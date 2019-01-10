package ws2.projetoes.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Horario extends BaseModel {

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Medico medico;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private DayOfWeek diaSemana;

    public Horario(LocalTime horaInicio,LocalTime horaFim,DayOfWeek diaSemana) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaSemana = diaSemana;
        System.out.println("d "+diaSemana);
    }

    public Horario(Horario horario)
    {

    }


    public void addMedico(Medico medico)
    {
        this.setMedico(medico);
    }

}