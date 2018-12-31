package ws2.projetoes.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Especialidade
{
    @Id
    private String nomeEspecialidade;

    /*@EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "especialidade")
    private Set<Medico> medicos = new HashSet<>();*/

    public Especialidade(String especialidade)
    {
        this.nomeEspecialidade = especialidade;
    }
}
