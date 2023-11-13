package com.co.banca.digital.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("SA")
@NoArgsConstructor
@AllArgsConstructor
public class CuentaAhorro extends CuentaBancaria{

    private double tasaDeInteres;
}
