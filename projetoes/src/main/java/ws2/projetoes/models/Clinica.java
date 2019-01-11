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
public class Clinica extends BaseModel
{
    private String localizacao;
    private String url;

    /*@EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "especialidade")
    private Set<Medico> medicos = new HashSet<>();*/

    public Clinica(String localizacao,String url)
    {
        this.localizacao = localizacao;
        this.url = "http://localhost:".concat(url);
    }
}