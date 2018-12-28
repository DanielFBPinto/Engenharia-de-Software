package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicoHorarioFilter implements FilterI<Medico>
{
    private LocalDate horaInicioFilter;
    private LocalDate horaFimFilter;

    public MedicoHorarioFilter (LocalDate horaInicio,LocalDate horaFim)
    {
        this.horaInicioFilter = horaInicio;
        this.horaFimFilter = horaFim;
    }

    @Override
    public Set<Medico> filter(Set<Medico>medicos)
    {
        if(horaInicioFilter == null)
        {
            return medicos;
        }
        return medicos.stream().filter(medico -> horaInicioFilter.equals(this.horaInicioFilter)).collect(Collectors.toSet());
    }
}
