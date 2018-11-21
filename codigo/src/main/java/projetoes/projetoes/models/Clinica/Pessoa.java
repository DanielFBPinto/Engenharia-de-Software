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
public class Pessoa
{
  private String nome;
  private Integer id;
  private Date dataNascimento;
  private Integer numCC;
}