package projetoes.projetoes.filters.horarioFilters;

import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;

import java.util.Set;
import java.util.Set;
import java.util.stream.Collectors;

public class horarioMedicoFilter implements horarioFilter{
    private Medico medicoToFilter;
    public horarioMedicoFilter( Medico medicoToFilter)
    {
        this.medicoToFilter = medicoToFilter;
    }
    @Override
    public Set<Horario> filter(Set<Horario> horarios) {
        if(medicoToFilter==null)
            return horarios;
        return horarios.stream().filter(horario-> horario.getMyMedico()==this.medicoToFilter).collect(Collectors.toSet());
    }

}
