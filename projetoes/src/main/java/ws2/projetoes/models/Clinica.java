package ws2.projetoes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonInclude
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Especialidade> especialidades= new HashSet<>();




    public Clinica(String localizacao,String url)
    {
        this.localizacao = localizacao;
        this.url = "http://localhost:".concat(url);
    }
    public void adicionarEspecialidades(Especialidade especialidade){
      this.especialidades.add(especialidade);
       especialidade.adicionarClinica(this);
    }


}