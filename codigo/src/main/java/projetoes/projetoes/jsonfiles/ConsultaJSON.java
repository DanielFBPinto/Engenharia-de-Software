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
public class ConsultaJSON extends BaseModel
{
    private Long nomePaciente;
    private Long nomeMedico;
    private LocalDateTime dataAntiga;
    private LocalDateTime novaData;

    public ConsultaJSON(Long nomePaciente,Long nomeMedico,LocalDateTime dataAntiga,LocalDateTime novaData)
    {
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.dataAntiga = dataAntiga;
        this.novaData = novaData;
    }

    public ConsultaJSON(Long nomePaciente,LocalDateTime dataAntiga)
    {
        this.dataAntiga = dataAntiga;
        this.nomePaciente = nomePaciente;
    }

    public ConsultaJSON(Long nomePaciente,Long nomeMedico,LocalDateTime dataNova)
    {
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomePaciente;
        this.novaData = dataNova;
    }

}
