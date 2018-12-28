package projetoes.projetoes.filters.medicoFilters;

import lombok.Data;
import projetoes.projetoes.filters.AndFilter;
import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;

import java.util.Set;

@Data
public class MedicoFilterService {
    private Integer cedulaMedica;
    private String especialidade;
    private Horario horario;

    public Set<Medico> filterMedicos(Set<Medico> medicos, MedicoFilterService medicoFilterService) {
        FilterI<Medico> medicoEspecialidadeFilter = new MedicoEspecialidadeFilter(medicoFilterService.getEspecialidade());
        FilterI<Medico> especialidadeHorario = new AndFilter<>(medicoEspecialidadeFilter);
        return especialidadeHorario.filter(medicos);
    }
}
