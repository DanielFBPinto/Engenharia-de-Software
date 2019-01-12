package projetoes.projetoes.jsonfiles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import projetoes.projetoes.models.BaseModel;
import projetoes.projetoes.models.Consulta;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MedicoJSON extends BaseModel
{
    private String nome;
    private Integer cedulaMedica;
    private LocalDateTime dataNascimento;
    private Integer numCC;
    private Long myid;
    private String especialidade;

    public MedicoJSON(Integer cedulaMedica, LocalDateTime dataNascimento, Integer numCC, Long myid, String especialidade)
    {
        this.cedulaMedica = cedulaMedica;
        this.dataNascimento = dataNascimento;
        this.numCC = numCC;
        this.myid = myid;
        this.especialidade = especialidade;
    }

    public MedicoJSON(Integer cedulaMedica, LocalDateTime dataNascimento, Integer numCC, String especialidade)
    {
        this.cedulaMedica = cedulaMedica;
        this.dataNascimento = dataNascimento;
        this.numCC = numCC;
        this.especialidade = especialidade;
    }

    public MedicoJSON(String nome,Integer numCC,Long myid)
    {
        this.nome = nome;
        this.numCC = numCC;
        this.myid = myid;
    }
}