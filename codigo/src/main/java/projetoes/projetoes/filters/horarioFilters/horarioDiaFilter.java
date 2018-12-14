package projetoes.projetoes.filters.horarioFilters;

import projetoes.projetoes.models.Horario;

import java.util.Set;
import java.util.stream.Collectors;

public class horarioDiaFilter implements horarioFilter {
    private String diaDaSemanaToFilter;
    public horarioDiaFilter( String diaDaSemanaToFilter)
    {
        this.diaDaSemanaToFilter = diaDaSemanaToFilter;
    }

    @Override
    public Set<Horario> filter(Set<Horario> horarios)
    {
        if(diaDaSemanaToFilter == null)
        {
            return horarios;
        }
        //return medicos.stream().filter(medico -> medico.getEspecialidade().compareTo(this.especialidadeToFilter) == 0).collect(Collectors.toSet());
        return horarios.stream().filter(horario -> horario.getDiaSemana().compareTo(this.diaDaSemanaToFilter)==0).collect(Collectors.toSet());
    }
}
