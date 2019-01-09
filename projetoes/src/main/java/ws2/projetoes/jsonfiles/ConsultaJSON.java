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
public class ConsultaJSON extends BaseModel
{
    private Long idClinica;
    private Long nomePaciente;
    private Long nomeMedico;
    private LocalDateTime dataAntiga;
    private LocalDateTime novaData;

    public ConsultaJSON(Long idClinica,Long nomePaciente,Long nomeMedico,LocalDateTime dataAntiga,LocalDateTime novaData)
    {
        this.idClinica=idClinica;
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.dataAntiga = dataAntiga;
        this.novaData = novaData;
    }

    public ConsultaJSON(Long idClinica,Long nomePaciente,LocalDateTime dataAntiga)
    {
        this.idClinica=idClinica;
        this.dataAntiga = dataAntiga;
        this.nomePaciente = nomePaciente;
    }

    public ConsultaJSON(Long idClinica,Long nomePaciente,Long nomeMedico,LocalDateTime dataNova)
    {
        this.idClinica=idClinica;
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomePaciente;
        this.novaData = dataNova;
    }

}
