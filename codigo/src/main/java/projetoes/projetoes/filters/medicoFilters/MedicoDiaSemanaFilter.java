package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class MedicoDiaSemanaFilter implements FilterI<Medico>
{
    private DayOfWeek dayOfWeekFilter;

    public MedicoDiaSemanaFilter(DayOfWeek dayOfWeek)
    {
        this.dayOfWeekFilter = dayOfWeek;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos)
    {
        if(dayOfWeekFilter == null)
        {
            return medicos;
        }
        Set<Medico> medicosFiltered = new HashSet<>();
        for(Medico m : medicos)
        {
            //Medico novoMedico = new Medico(m);
            for(Horario horario : m.getHorarios())
            {
                if(horario.getDiaSemana().equals(dayOfWeekFilter)) {
                    //novoMedico.addHorario(new Horario(horario));
                    //medicosFiltered.add(novoMedico);
                    medicosFiltered.add(m);
                }
            }
        }
        return medicosFiltered;
    }
}
