package projetoes.projetoes.jsonfiles;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import projetoes.projetoes.models.BaseModel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PacienteJSON extends BaseModel
{
    private String name;
    private LocalDateTime dataNascimento;
    private Integer numCC;
    private Long myid;
    private Long idClinica;

    public PacienteJSON(Long idClinica,String name, LocalDateTime dataNascimento, Integer numCC)
    {
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.numCC = numCC;
    }

    public PacienteJSON(Long idClinica,String name, LocalDateTime dataNascimento, Integer numCC, Long id)
    {
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.numCC = numCC;
        this.myid = id;
    }
}
