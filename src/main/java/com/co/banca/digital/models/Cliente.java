package com.co.banca.digital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)                                   //Herencia, Esto nos indica que todas las clases de una jerarquia se asignaran a una sola tabla.
@DiscriminatorColumn(name = "TIPO", length = 4)                                         //
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nombre;
    private String gmail;

    @OneToMany(mappedBy = "cliente")                                                    //mappedBy indica que ese "cliente" haría lo contrario a la relacion escrita, por lo tanto indica una relacion inversa.
    private List<CuentaBancaria> cuentaBancarias;
}
