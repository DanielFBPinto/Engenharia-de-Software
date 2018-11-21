package projetoes.projetoes.models.Clinica;
import java.util.ArrayList;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Funcionario extends Pessoa
{
  private int idFuncionario;
  private String cargo;
  private ArrayList<Horario> myHorario = new ArrayList<>();
}