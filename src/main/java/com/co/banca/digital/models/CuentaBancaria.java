package com.co.banca.digital.models;

import com.co.banca.digital.enums.EstadoCuenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)                                   //Herencia, Esto nos indica que todas las clases de una jerarquia se asignaran a una sola tabla.
@DiscriminatorColumn(name = "TIPO", length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaBancaria {

    @Id
    private String id;

    private double balance;
    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaBancaria",fetch = FetchType.LAZY)                        //fetch = FetchType.LAZY Quiere decir que cuando yo llame CuentaBancaria me va a traer esta lista de OperacionBancaria solo si yo lo llamo, si no, no la llama.
    private List<OperacionCuenta> operacionesCuenta;

}
