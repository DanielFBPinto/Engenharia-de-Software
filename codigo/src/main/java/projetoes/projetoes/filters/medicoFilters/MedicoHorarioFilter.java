package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
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
    public Set<Medico> filter(Set<Medico> medicos)
    {
        if(horaInicioFilter == null || horaFimFilter == null)
        {
            return medicos;
        }
        Set<Medico> medicosFiltered = new HashSet<>();
        for(Medico m : medicos)
        {
            Medico novoMedico = new Medico(m);
            for(Horario horario : m.getMyHorarioMedico())
            {
                if((horario.getHoraInicio().isAfter(LocalTime.from(horaInicioFilter)) || horario.getHoraInicio().equals(horaInicioFilter))
                && (horario.getHoraFim().isBefore(LocalTime.from(horaFimFilter)) || horario.getHoraFim().equals(horaFimFilter)))
                {
                    novoMedico.addHorario(new Horario(horario));
                    medicosFiltered.add(novoMedico);
                }
            }
        }
        return medicosFiltered;
    }
}
