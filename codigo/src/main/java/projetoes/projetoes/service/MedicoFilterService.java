package projetoes.projetoes.service;


import org.springframework.stereotype.Service;
import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.filters.medicoFilters.MedicoEspecialidadeFilter;
import projetoes.projetoes.models.Medico;

import java.util.Set;

@Service
public class MedicoFilterService
{

    public Set<Medico> doFilter(Set<Medico> entidades,MedicoFilterService medicoFilterService)
    {
        FilterI<Medico> especialidadeFilter = new MedicoEspecialidadeFilter(medicoFilterService.getEspecialidade());
    }



    public Set<Medico> doFilter(Set<Medico> entities, projetoes.projetoes.filters.medicoFilters.MedicoFilterService medicoFilterService) {
        FilterI<Medico> especialidadeFilter=new MedicoEspecialidadeFilter(medicoFilterService.getEspecialidade());
                FilterI<Medico> horarioDiaFilter=new HorarioDiaFilter(medicoFilterService.getHorario().getDiaSemana());
    }
}
