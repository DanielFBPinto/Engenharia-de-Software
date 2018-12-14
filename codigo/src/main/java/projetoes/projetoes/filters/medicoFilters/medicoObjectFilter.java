package projetoes.projetoes.filters.medicoFilters;

import lombok.Data;
import projetoes.projetoes.models.Horario;

@Data
public class medicoObjectFilter
{
    private Integer cedulaMedica;
    private String especialidade;
    private Horario horario;
}
