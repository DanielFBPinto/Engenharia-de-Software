package projetoes.projetoes.models;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Consulta extends BaseModel
{
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne
  private Medico myMedico;
  private LocalDateTime data;
  private int precoConsulta;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne
  private Paciente myPaciente;


  public Consulta(LocalDateTime data)
  {
    this.data = data;
  }

  public Consulta(LocalDateTime data,Medico medico,Paciente paciente)
  {
    this.data = data;
    this.myMedico = medico;
    this.myPaciente = paciente;
  }

  public Consulta(int precoConsulta)
  {
    this.precoConsulta = precoConsulta;
  }
  public void addMedico(Medico medico)
  {
    this.myMedico = medico;
  }

  public void addPaciente(Paciente paciente)
  {
    this.myPaciente = paciente;
  }
}