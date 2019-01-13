package ws2.projetoes.filter;

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
