package br.com.fiap.davinciEnergy.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="DISPOSITIVO")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dispositivo_id")
    private Long id;

    @NotBlank
    @Column(name="nome")
    private String nome;

    @Column(name="whatts")
    private Double whatts;

    @Column(name="tipos")
    private Tipos tipos;

    @Column(name="tempo")
    private Tempo tempo;


    @OneToOne
    @JoinColumn(name="medidor")
    private Medidor medidor;





}
