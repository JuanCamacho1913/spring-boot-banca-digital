package com.co.banca.digital.services;

import com.co.banca.digital.models.CuentaActual;
import com.co.banca.digital.models.CuentaAhorro;
import com.co.banca.digital.models.CuentaBancaria;
import com.co.banca.digital.repositories.CuentaBancariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BancoService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    public void consultar(){
        CuentaBancaria cuentaBancariaBBDD = cuentaBancariaRepository.findById("0727844a-1212-4410-9549-c34401472c4e").orElse(null);

        if (cuentaBancariaBBDD != null){
            System.out.println("****************************************");
            System.out.println("ID: " + cuentaBancariaBBDD.getId());
            System.out.println("Balance de la cuenta: " + cuentaBancariaBBDD.getBalance());
            System.out.println("Estado: " + cuentaBancariaBBDD.getEstadoCuenta());
            System.out.println("Fecha de creacion: " + cuentaBancariaBBDD.getFechaCreacion());
            System.out.println("Cliente: " + cuentaBancariaBBDD.getCliente().getNombre());
            System.out.println("Nombre de la clase" + cuentaBancariaBBDD.getClass().getName());
        }

        if (cuentaBancariaBBDD instanceof CuentaActual){
            System.out.println("Sobregiro: " + ((CuentaActual) cuentaBancariaBBDD).getSobregiro());
        } else if (cuentaBancariaBBDD instanceof CuentaAhorro) {
            System.out.println("Tasa de interes: " + ((CuentaAhorro) cuentaBancariaBBDD).getTasaDeInteres());
        }

        cuentaBancariaBBDD.getOperacionesCuenta().forEach(operacionCuenta -> {
            System.out.println("------------------------------------------");
            System.out.println("Tipo de operacion: " + operacionCuenta.getTipoOperacion());
            System.out.println("Fecha de operacion: " + operacionCuenta.getFechaOperacion());
            System.out.println("Monto: " + operacionCuenta.getMonto());
        });
    }
}
