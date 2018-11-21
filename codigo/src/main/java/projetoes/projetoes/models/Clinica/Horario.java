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
public class Horario
{
  private Date horaInicio;
  private Date horaFim;
  private String diaSemana;
  private Funcionario myFuncionario;
}