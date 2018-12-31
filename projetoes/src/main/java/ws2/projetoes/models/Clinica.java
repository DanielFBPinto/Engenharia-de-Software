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
public class Clinica
{
    @Id
    private String nome;
    private String localizacao;

    /*@EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "especialidade")
    private Set<Medico> medicos = new HashSet<>();*/

    public Clinica(String nome, String localizacao)
    {
        this.nome = nome;
        this.localizacao=localizacao;
    }
}