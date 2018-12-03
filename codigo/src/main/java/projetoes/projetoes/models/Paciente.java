package projetoes.projetoes.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Paciente extends Pessoa
{

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
  private Set<Consulta> myConsulta = new HashSet<>();
  public void marcarConsulta(Date data)
  {

  }

  public void cancelarConsulta(Date date)
  {

  }

  public void alterarCOnsulta(Date data)
  {

  }

  public Paciente(String name) {
    super(name);
  }
}