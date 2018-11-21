package projetoes.projetoes.models.Clinica;
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
public class Consulta
{
  private Date data;
  private int precoConsulta;
  private int idConsulta;
  private Paciente myPaciente;
  private Medico myMedico;
}