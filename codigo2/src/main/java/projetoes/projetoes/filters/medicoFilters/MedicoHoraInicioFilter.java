package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class MedicoHoraInicioFilter implements FilterI<Medico>
{
    private LocalTime horaInicioFilter;

    public MedicoHoraInicioFilter (LocalTime horaInicio)
    {

        this.horaInicioFilter = horaInicio;
    }
    @Override
    public Set<Medico> filter(Set<Medico> medicos)
    {
        if(horaInicioFilter == null)
        {
            return medicos;
        }
        Set<Medico> medicosFiltered = new HashSet<>();
        for(Medico m : medicos)
        {
            //Medico novoMedico = new Medico(m);
            for(Horario horario : m.getHorarios())
            {
                if((horario.getHoraInicio().isBefore(LocalTime.from(horaInicioFilter)) || horario.getHoraInicio().equals(horaInicioFilter)))
                {
                 //   novoMedico.addHorario(new Horario(horario));
                //    medicosFiltered.add(novoMedico);
                    medicosFiltered.add(m);
                }
            }
        }
        return medicosFiltered;
    }
}
