package projetoes.projetoes.filters.medicoFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Medico;

import java.util.Set;

public interface medicoFilter extends FilterI<Medico>
{
    Set<Medico> filter(Set<Medico> entities);
}
