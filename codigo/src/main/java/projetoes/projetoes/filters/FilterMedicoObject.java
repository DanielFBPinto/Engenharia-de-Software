package projetoes.projetoes.filters;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
public class FilterMedicoObject
{
    private String especialidade;
    private DayOfWeek diaSemana;
    private LocalDate horaInicio;
    private LocalDate horaFim;
}
