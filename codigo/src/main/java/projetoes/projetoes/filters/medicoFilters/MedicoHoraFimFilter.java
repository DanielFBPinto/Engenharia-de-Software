package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class MedicoHoraFimFilter implements FilterI<Medico>
{

    private LocalDate horaFimFilter;

    public MedicoHoraFimFilter (LocalDate horaFim)
    {

        this.horaFimFilter = horaFim;
    }

    @Override
    public Set<Medico> filter(Set<Medico> medicos)
    {
        if( horaFimFilter == null)
        {
            return medicos;
        }
        Set<Medico> medicosFiltered = new HashSet<>();
        for(Medico m : medicos)
        {
            Medico novoMedico = new Medico(m);
            for(Horario horario : m.getHorarios())
            {
                if((horario.getHoraFim().isBefore(LocalTime.from(horaFimFilter)) || horario.getHoraFim().equals(horaFimFilter)))
                {
                    //novoMedico.addHorario(new Horario(horario));
                   // medicosFiltered.add(novoMedico);
                    medicosFiltered.add(m);
                }
            }
        }
        return medicosFiltered;
    }
}
