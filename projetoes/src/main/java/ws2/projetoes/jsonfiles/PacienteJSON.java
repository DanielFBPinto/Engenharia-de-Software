package ws2.projetoes.jsonfiles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ws2.projetoes.models.BaseModel;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PacienteJSON extends BaseModel
{
    private Long idClinica;
    private String name;
    private LocalDateTime dataNascimento;
    private Integer numCC;
    private Long myid;

    public PacienteJSON(Long idClinica,String name, LocalDateTime dataNascimento, Integer numCC) {
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.numCC = numCC;
        this.idClinica=idClinica;
    }

    public PacienteJSON(Long idClinica,String name, LocalDateTime dataNascimento, Integer numCC, Long id) {
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.numCC = numCC;
        this.myid = id;
        this.idClinica=idClinica;
    }
}
