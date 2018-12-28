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

    public FilterMedicoObject(String especialidade)
    {
        this.especialidade = especialidade;
    }

    public FilterMedicoObject(LocalDate horaFim,LocalDate horaInicio)
    {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public FilterMedicoObject(DayOfWeek diaSemana)
    {
        this.diaSemana = diaSemana;
    }
}
