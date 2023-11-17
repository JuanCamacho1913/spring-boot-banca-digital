package com.co.banca.digital.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("SA")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)                                      //Esta anotacion es para solucionar un Warning de implementacion.
public class CuentaAhorro extends CuentaBancaria{

    private double tasaDeInteres;
}
