package com.co.banca.digital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String gmail;

    @OneToMany(mappedBy = "cliente")                                                    //mappedBy indica que ese "cliente" haría lo contrario a la relacion escrita, por lo tanto indica una relacion inversa.
    private List<CuentaBancaria> cuentasBancarias;
}
