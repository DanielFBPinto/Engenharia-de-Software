package projetoes.projetoes.jsonfiles;

import lombok.Getter;
import lombok.Setter;
import projetoes.projetoes.models.Consulta;

import java.time.LocalDateTime;

@Getter
@Setter


public class ConsultaJSON
{
    private String nomePaciente;
    private String nomeMedico;
    private LocalDateTime dataAntiga;
    private LocalDateTime novaData;

    public ConsultaJSON(String nomePaciente,String nomeMedico,LocalDateTime dataAntiga,LocalDateTime novaData)
    {
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.dataAntiga = dataAntiga;
        this.novaData = novaData;
    }

    public ConsultaJSON(String nomePaciente,LocalDateTime dataAntiga)
    {
        this.dataAntiga = dataAntiga;
        this.nomePaciente = nomePaciente;
    }

    public ConsultaJSON(String nomePaciente,String nomeMedico,LocalDateTime data)
    {
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.novaData = data;

    }


}
