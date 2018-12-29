package projetoes.projetoes.filters.medicoFilters;

import lombok.Data;
import org.springframework.stereotype.Service;
import projetoes.projetoes.filters.AndFilter;
import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.filters.FilterMedicoObject;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;

import java.util.Set;


@Service
public class MedicoFilterService {
    public Set<Medico> filterMedicos(Set<Medico> medicos, FilterMedicoObject filterMedicoObject) {
        FilterI<Medico> medicoDiaSemanaFilter = new MedicoDiaSemanaFilter(filterMedicoObject.getDiaSemana());
        FilterI<Medico> medicoHorarioFilter = new MedicoHorarioFilter(filterMedicoObject.getHoraInicio(),filterMedicoObject.getHoraFim());
        FilterI<Medico> diaSemanaHorarioFilter = new AndFilter<>(medicoDiaSemanaFilter,medicoHorarioFilter);
        FilterI<Medico> medicoEspecialidadeFilter = new MedicoEspecialidadeFilter(filterMedicoObject.getEspecialidade());
        FilterI<Medico> especialidadeoHorarioFilter = new AndFilter<>(medicoEspecialidadeFilter,diaSemanaHorarioFilter);
        return especialidadeoHorarioFilter.filter(medicos);
    }
}
