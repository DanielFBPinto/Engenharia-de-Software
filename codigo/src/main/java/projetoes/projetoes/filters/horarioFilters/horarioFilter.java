package projetoes.projetoes.filters.horarioFilters;

import projetoes.projetoes.filters.FilterI;
import projetoes.projetoes.models.Horario;

import java.util.Set;

public interface horarioFilter extends FilterI<Horario> {
    Set<Horario> filter(Set<Horario> entities);
}
