package projetoes.projetoes.models.Clinica;
import java.util.ArrayList;
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
public class Paciente extends Pessoa
{
  private ArrayList<Consulta> myConsulta = new ArrayList<>();

  public void marcarConsulta(Date data)
  {

  }

  public void cancelarConsulta(Date date)
  {

  }

  public void alterarCOnsulta(Date data)
  {

  }

}