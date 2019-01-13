package projetoes.projetoes.filters;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class FilterMedicoObject
{
    private String especialidade;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
}
