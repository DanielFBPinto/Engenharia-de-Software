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
public class Medico extends Pessoa
{
  private Integer cedulaMedica;
  private String especialidade;
  private ArrayList<Consulta> myConsulta = new ArrayList<>();
  private ArrayList<Horario> myHorarioMedico = new ArrayList<>();
}