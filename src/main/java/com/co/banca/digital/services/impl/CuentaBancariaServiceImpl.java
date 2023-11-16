package com.co.banca.digital.services.impl;

import com.co.banca.digital.enums.TipoOperacion;
import com.co.banca.digital.exceptions.BalanceInsuficienteException;
import com.co.banca.digital.exceptions.ClienteNotFoundException;
import com.co.banca.digital.exceptions.CuentaBancariaNotFoundException;
import com.co.banca.digital.models.*;
import com.co.banca.digital.repositories.ClienteRepository;
import com.co.banca.digital.repositories.CuentaBancariaRepository;
import com.co.banca.digital.repositories.OperacionCuentaRepository;
import com.co.banca.digital.services.CuentaBancariaService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
//@Slf4j es una librería Java que funciona como una abstracción simple para varios frameworks de logging (como Log4J, java. util. logging o Logback). Le permite al usuario final agregar el framework de log deseado al momento de deploy.
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;

    @Override
    public Cliente saveCliente(Cliente cliente) {
        log.info("Guardando un nuevo cliente");
        Cliente clienteBBDD = clienteRepository.save(cliente);
        return clienteBBDD;
    }

    @Override
    public CuentaActual saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cliente == null) {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }

        CuentaActual cuentaActual = new CuentaActual();
        cuentaActual.setId(UUID.randomUUID().toString());
        cuentaActual.setFechaCreacion(new Date());
        cuentaActual.setBalance(balanceInicial);
        cuentaActual.setSobregiro(sobregiro);
        cuentaActual.setCliente(cliente);

        CuentaActual cuentaActualBBDD = cuentaBancariaRepository.save(cuentaActual);
        return cuentaActualBBDD;
    }

    @Override
    public CuentaAhorro saveCuentaBancariaAhorro(double balanceInicial, double tasaInteres, Long clienteId) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cliente == null) {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }

        CuentaAhorro cuentaAhorro = new CuentaAhorro();
        cuentaAhorro.setId(UUID.randomUUID().toString());
        cuentaAhorro.setFechaCreacion(new Date());
        cuentaAhorro.setBalance(balanceInicial);
        cuentaAhorro.setTasaDeInteres(tasaInteres);
        cuentaAhorro.setCliente(cliente);

        CuentaAhorro cuentaAhorroBBDD = cuentaBancariaRepository.save(cuentaAhorro);
        return cuentaAhorroBBDD;
    }

    @Override
    public List<Cliente> listClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException {
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findById(cuentaId)
                .orElseThrow(() -> new CuentaBancariaNotFoundException("Cuenta bancaria no encontrada"));
        return cuentaBancaria;
    }

    @Override
    public void debit(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
        CuentaBancaria cuentaBancaria = getCuentaBancaria(cuentaId);

        if (cuentaBancaria.getBalance() < monto) {
            throw new BalanceInsuficienteException("Balance insuficiente");
        }

        OperacionCuenta operacionCuenta = new OperacionCuenta();
        operacionCuenta.setTipoOperacion(TipoOperacion.DEBITO);
        operacionCuenta.setMonto(monto);
        operacionCuenta.setDescripcion(descripcion);
        operacionCuenta.setFechaOperacion(new Date());
        operacionCuenta.setCuentaBancaria(cuentaBancaria);
        operacionCuentaRepository.save(operacionCuenta);
        cuentaBancaria.setBalance(cuentaBancaria.getBalance() - monto);                                 //Tenemos que actualizar la cuenta bancaria.
        cuentaBancariaRepository.save(cuentaBancaria);
    }

    @Override
    public void credit(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException {
        CuentaBancaria cuentaBancaria = getCuentaBancaria(cuentaId);

        OperacionCuenta operacionCuenta = new OperacionCuenta();
        operacionCuenta.setTipoOperacion(TipoOperacion.CREDITO);
        operacionCuenta.setMonto(monto);
        operacionCuenta.setDescripcion(descripcion);
        operacionCuenta.setFechaOperacion(new Date());
        operacionCuenta.setCuentaBancaria(cuentaBancaria);
        operacionCuentaRepository.save(operacionCuenta);
        cuentaBancaria.setBalance(cuentaBancaria.getBalance() + monto);
        cuentaBancariaRepository.save(cuentaBancaria);
    }

    @Override
    public void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto) throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
        debit(cuentaIdPropietario, monto, "Transferencia a: " + cuentaIdDestinatario);
        credit(cuentaIdDestinatario, monto, "Transferencia de: " + cuentaIdPropietario);
    }

    @Override
    public List<CuentaBancaria> listCuentasBancarias() {
        return cuentaBancariaRepository.findAll();
    }
}
