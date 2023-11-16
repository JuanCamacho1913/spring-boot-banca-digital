package com.co.banca.digital;

import com.co.banca.digital.enums.EstadoCuenta;
import com.co.banca.digital.enums.TipoOperacion;
import com.co.banca.digital.models.*;
import com.co.banca.digital.repositories.ClienteRepository;
import com.co.banca.digital.repositories.CuentaBancariaRepository;
import com.co.banca.digital.repositories.OperacionCuentaRepository;
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
    @Bean
    CommandLineRunner start(CuentaBancariaService cuentaBancariaService) {
        return args -> {
            Stream.of("Julian", "Samuel", "Juan", "Jhonier").forEach(nombre -> {
                Cliente cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setGmail(nombre + "@gmail.com");
                cuentaBancariaService.saveCliente(cliente);
            });

            cuentaBancariaService.listClientes().forEach(cliente -> {
                try {
                    cuentaBancariaService.saveCuentaBancariaActual(Math.random() * 90000, 9000, cliente.getId());
                    cuentaBancariaService.saveCuentaBancariaAhorro(120000, 5.5, cliente.getId());

                    List<CuentaBancaria> cuentaBancarias = cuentaBancariaService.listCuentasBancarias();

                    for (CuentaBancaria cuentaBancaria : cuentaBancarias) {
                        for (int i = 0; i < 10; i++) {
                            cuentaBancariaService.credit(cuentaBancaria.getId(), 10000 + Math.random() * 120000, "Credito");
                            cuentaBancariaService.debit(cuentaBancaria.getId(), 1000 + Math.random() * 9000, "Debito");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        };
    }
}
