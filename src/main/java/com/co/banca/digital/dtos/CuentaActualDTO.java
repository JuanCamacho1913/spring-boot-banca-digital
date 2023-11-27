package com.co.banca.digital.dtos;

import com.co.banca.digital.enums.EstadoCuenta;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class CuentaActualDTO extends CuentaBancariaDTO{

    private String id;

    private double balance;

    private Date fechaCreacion;

    private EstadoCuenta estadoCuenta;

    private ClienteDTO clienteDTO;

    private double sobregiro;



}
