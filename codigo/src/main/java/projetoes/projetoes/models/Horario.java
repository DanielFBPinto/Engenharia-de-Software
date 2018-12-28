package projetoes.projetoes.models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private DayOfWeek diaSemana;

    public Horario(LocalTime horaInicio,LocalTime horaFim,DayOfWeek diaSemana) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaSemana = diaSemana;
    }

    public Horario(Horario horario)
    {

    }

    public void addMedico(Medico medico)
    {
        this.setMyMedico(medico);
    }
}