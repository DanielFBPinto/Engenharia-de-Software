package projetoes.projetoes.jsonfiles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import projetoes.projetoes.models.BaseModel;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HorarioJSON extends BaseModel
{
    private Long medico;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private DayOfWeek diaSemana;

    public HorarioJSON(Long medico, LocalTime horaInicio, LocalTime horaFim, DayOfWeek diaSemana) {
        this.medico = medico;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaSemana = diaSemana;
    }
}
