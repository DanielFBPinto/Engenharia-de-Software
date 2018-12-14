package projetoes.projetoes.filters.horarioFilters;

import lombok.Data;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.models.Medico;

@Data
public class horarioObjectFilter {
    private String diaDaSemana;
    private Medico medico;
}
