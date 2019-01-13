package projetoes.projetoes.filters;

import projetoes.projetoes.models.BaseModel;

import java.util.Set;

public interface FilterI<T extends BaseModel> {
    Set<T> filter(Set<T> entities);
}
