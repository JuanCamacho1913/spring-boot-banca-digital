package com.co.banca.digital;

import com.co.banca.digital.dtos.ClienteDTO;
import com.co.banca.digital.dtos.CuentaActualDTO;
import com.co.banca.digital.dtos.CuentaAhorroDTO;
import com.co.banca.digital.dtos.CuentaBancariaDTO;
import com.co.banca.digital.models.*;
import com.co.banca.digital.services.BancoService;
import com.co.banca.digital.services.CuentaBancariaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ApiBancaDigitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBancaDigitalApplication.class, args);
    }


    //@Bean
    CommandLineRunner commandLineRunner(BancoService bancoService) {
        return args -> {
            bancoService.consultar();
        };
    }

    //Insertando datos de prueba.
    //@Bean
    CommandLineRunner start(CuentaBancariaService cuentaBancariaService) {
        return args -> {
            Stream.of("Julian", "Samuel", "Juan", "Jhonier").forEach(nombre -> {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setNombre(nombre);
                cliente.setGmail(nombre + "@gmail.com");
                cuentaBancariaService.saveCliente(cliente);
            });

            cuentaBancariaService.listClientes().forEach(cliente -> {
                try {
                    cuentaBancariaService.saveCuentaBancariaActual(Math.random() * 90000, 9000, cliente.getId());
                    cuentaBancariaService.saveCuentaBancariaAhorro(120000, 5.5, cliente.getId());

                    List<CuentaBancariaDTO> cuentaBancarias = cuentaBancariaService.listCuentasBancarias();

                    for (CuentaBancariaDTO cuentaBancaria : cuentaBancarias) {
                        for (int i = 0; i < 10; i++) {
                            String cuentaId;

                            if (cuentaBancaria instanceof CuentaAhorroDTO){
                                cuentaId = ((CuentaAhorroDTO) cuentaBancaria).getId();
                            }else {
                                cuentaId = ((CuentaActualDTO) cuentaBancaria).getId();
                            }

                            cuentaBancariaService.credit(cuentaId, 10000 + Math.random() * 120000, "Credito");
                            cuentaBancariaService.debit(cuentaId, 1000 + Math.random() * 9000, "Debito");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        };
    }
}
