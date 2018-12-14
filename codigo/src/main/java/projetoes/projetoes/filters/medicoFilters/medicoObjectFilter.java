package projetoes.projetoes.filters.medicoFilters;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import projetoes.projetoes.filters.AndFilter;
import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.filters.horarioFilters.horarioMedicoFilter;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;

@Data
public class medicoObjectFilter
{
    private Integer cedulaMedica;
    private String especialidade;
    private Horario horario;

    public ResponseEntity<Iterable<Medico>> filterMedicos(Iterable<Medico> allMedicos, medicoObjectFilter medicoObjectFilter)
    {
        FilterI<Medico> medicoEspecialidadeFilter = new medicoEspecialidadeFilter(medicoObjectFilter.getEspecialidade());
        FilterI<Medico> medicoHorarioFilter = new horarioMedicoFilter(medicoObjectFilter.getHorario());
        FilterI<Medico> especialidadeEhorarioFilter = new AndFilter<>(medicoEspecialidadeFilter,medicoHorarioFilter);
        return especialidadeEhorarioFilter.filter(allMedicos);
    }
}
