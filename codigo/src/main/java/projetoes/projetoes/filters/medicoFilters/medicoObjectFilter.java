package projetoes.projetoes.filters.medicoFilters;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import projetoes.projetoes.filters.AndFilter;
import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.filters.horarioFilters.horarioMedicoFilter;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;

import java.util.Set;
import java.util.logging.Filter;

@Data
public class medicoObjectFilter {
    private Integer cedulaMedica;
    private String especialidade;
    private Horario horario;

    public Set<Medico> filterMedicos(Set<Medico> medicos, medicoObjectFilter medicoObjectFilter) {
        FilterI<Medico> medicoEspecialidadeFilter = new medicoEspecialidadeFilter(medicoObjectFilter.getEspecialidade());
        FilterI<Medico> medicoCedulaMedica = new medicoCedulaMedicaFilter(medicoObjectFilter.getCedulaMedica());
        FilterI<Medico> especialidadeHorarioANDCedulaMedica = new AndFilter<>(medicoEspecialidadeFilter, medicoCedulaMedica);
        return especialidadeHorarioANDCedulaMedica.filter(medicos);
    }
}
