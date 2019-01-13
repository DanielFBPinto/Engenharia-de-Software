package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.models.Medico;
import java.util.HashSet;
import java.util.Set;

public class MedicoEspecialidadeFilter implements MedicoFilter {
    private String especialidadeToFilter;

    public MedicoEspecialidadeFilter(String especialidadeToFilter) {
        this.especialidadeToFilter = especialidadeToFilter;
    }

    /*@Override
    public Set<Medico> filter(Set<Medico> medicos) {
        if (especialidadeToFilter == null) {
            return medicos;
        }
        return medicos.stream()
                .filter(medico -> medico.getEspecialidade().compareTo(this.especialidadeToFilter) == 0)
                .collect(Collectors.toSet());
    }*/

    @Override
    public Set<Medico> filter(Set<Medico> medicos)
    {
        if(especialidadeToFilter == null)
        {
            return medicos;
        }
        Set<Medico> medicosFiltered = new HashSet<>();
        for(Medico m : medicos)
        {
           // Medico novoMedico = new Medico(m);
            if(m.getEspecialidade().getNomeEspecialidade().equals(especialidadeToFilter))
            {
                medicosFiltered.add(m);
            }
        }
        return medicosFiltered;
    }
}
