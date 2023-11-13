package com.co.banca.digital.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("CA")
@NoArgsConstructor
@AllArgsConstructor
public class CuentaActual extends CuentaBancaria{

    private double sobregiro;
}
