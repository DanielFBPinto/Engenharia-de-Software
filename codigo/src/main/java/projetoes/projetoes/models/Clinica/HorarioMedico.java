package projetoes.projetoes.models.Clinica;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class HorarioMedico extends Horario
{
  private String sala;
  private Medico myMedico;
}