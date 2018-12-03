package projetoes.projetoes.models;
import java.util.ArrayList;
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
public class Medico extends Funcionario {
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

  public String getName(){
    return super.getName();
  }
  public Medico(Integer cedulaMedica, String especialidade, String name) {
    super(name);
    this.cedulaMedica = cedulaMedica;
    this.especialidade = especialidade;

  }

  public void addConsulta(Consulta consulta){
    this.myConsulta.add(consulta);
    consulta.addMedico(this);
  }

}