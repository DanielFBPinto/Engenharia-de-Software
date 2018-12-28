package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.models.Medico;

import java.util.Set;
import java.util.stream.Collectors;

public class MedicoEspecialidadeFilter implements MedicoFilter {
    private String especialidadeToFilter;

    public MedicoEspecialidadeFilter(String especialidadeToFilter) {
        this.especialidadeToFilter = especialidadeToFilter;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if (especialidadeToFilter == null) {
            return medicos;
        }
        return medicos.stream()
                .filter(medico -> medico.getEspecialidade().compareTo(this.especialidadeToFilter) == 0)
                .collect(Collectors.toSet());
    }
}