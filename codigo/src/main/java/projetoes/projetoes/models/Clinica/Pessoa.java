package projetoes.projetoes.models.Clinica;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Pessoa extends BaseModel
{
  private String name;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime dataNascimento;
  private Integer numCC;
}