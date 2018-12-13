package projetoes.projetoes.models;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Horario extends BaseModel
{
  private LocalDateTime horaInicio;
  private LocalDateTime horaFim;
  private String diaSemana;
  //@ManyToMany(cascade = CascadeType.ALL)
  //private Set<Horario> myHorario = new HashSet<>();

  public Horario(LocalDateTime horaInicio,LocalDateTime horaFim,String diaSemana)
  {
    this.horaInicio = horaInicio;
    this.horaFim = horaFim;
    this.diaSemana = diaSemana;
  }
}