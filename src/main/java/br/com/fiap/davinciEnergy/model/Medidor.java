package br.com.fiap.davinciEnergy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="MEDIDOR")
public class Medidor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="medidor_id")
    private Long id;

    @NotBlank(message = "medidor")
    @Column(name="nome")
    private String nome;

}
