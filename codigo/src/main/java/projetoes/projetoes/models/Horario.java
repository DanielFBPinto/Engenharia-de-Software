package projetoes.projetoes.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Horario extends BaseModel {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Medico myMedico;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private String diaSemana;

    public Horario(LocalDateTime horaInicio, LocalDateTime horaFim, String diaSemana, Medico medico) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaSemana = diaSemana;
        this.myMedico = medico;
    }
}