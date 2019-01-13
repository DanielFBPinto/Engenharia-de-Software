package ws2.projetoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy ="especialidades")
    private Set<Clinica> clinicas= new HashSet<>();

    /*@EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "especialidade")
    private Set<Medico> medicos = new HashSet<>();*/

    public Especialidade(String especialidade)
    {
        this.nomeEspecialidade = especialidade;
    }


    public void adicionarClinica(Clinica clinica){
        this.clinicas.add(clinica);

    }
}
