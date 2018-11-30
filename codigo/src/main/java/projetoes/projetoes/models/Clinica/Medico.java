package projetoes.projetoes.models.Clinica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Medico extends Pessoa
{
  private Integer cedulaMedica;
  private String especialidade;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL)
  private Set<Consulta> myConsulta = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL)
  private Set<Horario> myHorarioMedico = new HashSet<>();


  public Medico(Integer cedulaMedica)
  {
    this.cedulaMedica = cedulaMedica;
  }

  public Medico(String especialidade)
  {
    this.especialidade = especialidade;
  }

  public Medico(Integer cedulaMedica,String especialidade)
  {
    this.especialidade = especialidade;
    this.cedulaMedica = cedulaMedica;
  }

  public void addConsulta(Consulta consulta)
  {
    this.myConsulta.add(consulta);
  }
}

