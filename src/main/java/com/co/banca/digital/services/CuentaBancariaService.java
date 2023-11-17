package com.co.banca.digital.services;

import com.co.banca.digital.dtos.ClienteDTO;
import com.co.banca.digital.exceptions.BalanceInsuficienteException;
import com.co.banca.digital.exceptions.ClienteNotFoundException;
import com.co.banca.digital.exceptions.CuentaBancariaNotFoundException;
import com.co.banca.digital.models.Cliente;
import com.co.banca.digital.models.CuentaActual;
import com.co.banca.digital.models.CuentaAhorro;
import com.co.banca.digital.models.CuentaBancaria;

import java.util.List;

public interface CuentaBancariaService {

    ClienteDTO saveCliente(ClienteDTO clienteDTO);

    ClienteDTO getCliente(Long clienteId) throws ClienteNotFoundException;

    ClienteDTO updateCliente(ClienteDTO clienteDTO);

    void deleteCliente(Long clienteId);

    CuentaActual saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId) throws ClienteNotFoundException;

    CuentaAhorro saveCuentaBancariaAhorro(double balanceInicial, double tasaInteres, Long clienteId) throws ClienteNotFoundException;

    List<ClienteDTO> listClientes();

    CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException;

    void debit(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;

    void credit(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException;

    void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;

    List<CuentaBancaria> listCuentasBancarias();
}
