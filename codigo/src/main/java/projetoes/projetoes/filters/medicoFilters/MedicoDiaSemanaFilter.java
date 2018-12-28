package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
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

        Set<Medico> medicosFiltered = new HashSet<>();
        for(Medico m : medicos)
        {
            Medico novoMedico = new Medico(m);
            for(Horario horario : m.getMyHorarioMedico())
            {
                if(horario.getDiaSemana().equals(DayOfWeek.from(dayOfWeekFilter))) {
                    novoMedico.addHorario(new Horario(horario));
                    medicosFiltered.add(novoMedico);
                }
                }
        }
        return medicosFiltered;
    }
}