package projetoes.projetoes.models.Clinica;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
  private int idConsulta;
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne
  private Paciente myPaciente;

}