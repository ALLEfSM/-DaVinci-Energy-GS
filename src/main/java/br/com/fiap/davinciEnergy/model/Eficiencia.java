package br.com.fiap.davinciEnergy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="EFICIENCIA")
public class Eficiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="eficiencia_id")
    private Long id;

    @Column(name="tipos")
    private Tipos tipos;


    @ManyToOne
    @JoinColumn(name="dispositivo_id")
    private Dispositivo dispositivo;

    private Double potencia;

    public Double calcularMediaFinal() {
        // Validar se os dados necessários estão presentes
        if (dispositivo == null || dispositivo.getWhatts() == null || potencia == null) {
            throw new IllegalStateException("Dispositivo, potência ou tempo de uso não informado.");
        }
        // Fórmula de consumo mensal (30 dias)
        return (potencia * dispositivo.getWhatts() * 30) / 1000; // Retorna em kWh
    }


}
