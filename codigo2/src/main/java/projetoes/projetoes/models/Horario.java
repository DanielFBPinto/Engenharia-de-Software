package projetoes.projetoes.models;

import java.time.DayOfWeek;
import java.time.LocalTime;
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
        this.diaSemana = horario.getDiaSemana();
        this.horaFim = horario.getHoraFim();
        this.horaInicio = horario.getHoraInicio();
        this.medico = horario.getMedico();
    }


    public void addMedico(Medico medico)
    {
        this.setMedico(medico);
    }

}