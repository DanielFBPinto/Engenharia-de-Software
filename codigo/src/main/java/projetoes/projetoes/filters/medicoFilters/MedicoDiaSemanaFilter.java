package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Medico;
import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicoDiaSemanaFilter implements FilterI<Medico>
{
    private DayOfWeek dayOfWeekFilter;

    public MedicoDiaSemanaFilter(DayOfWeek dayOfWeek)
    {
        this.dayOfWeekFilter = dayOfWeek;
    }

    public Set<Medico> filter(Set<Medico> medicos)
    {
        if(dayOfWeekFilter == null)
        {
            return medicos;
        }
        return medicos.stream().filter(medico -> dayOfWeekFilter.equals(this.dayOfWeekFilter)).collect(Collectors.toSet());
    }
}
