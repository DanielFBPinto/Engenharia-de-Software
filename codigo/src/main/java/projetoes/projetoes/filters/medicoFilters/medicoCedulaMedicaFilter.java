package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.models.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public class medicoCedulaMedicaFilter implements medicoFilter
{
    private Integer cedulaMedicaToFilter;

    public medicoCedulaMedicaFilter( Integer cedulaMedicaToFilter)
    {
        this.cedulaMedicaToFilter = cedulaMedicaToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos)
    {
        if(cedulaMedicaToFilter == 0)
        {
            return medicos;
        }
        return medicos.stream().filter(medico -> medico.getCedulaMedica().equals(this.cedulaMedicaToFilter)).collect(Collectors.toSet());
    }
}

