package ws2.projetoes.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Consulta extends BaseModel {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Medico medico;
    private LocalDateTime data;
    private LocalDateTime datafim;
    private DayOfWeek dia;
    private int precoConsulta;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Paciente paciente;

    public Consulta(LocalDateTime data) {
        this.data = data;
        this.datafim=data.plusMinutes(15);
        this.dia=data.getDayOfWeek();
    }

    public Consulta(LocalDateTime data, Medico medico, Paciente paciente) {
        this.data = data;
        this.datafim=data.plusMinutes(15);
        this.dia=data.getDayOfWeek();
        this.medico = medico;
        this.paciente = paciente;
    }

    public Consulta(int precoConsulta) {
        this.precoConsulta = precoConsulta;
    }

    public void addMedico(Medico medico) {
        this.medico = medico;
    }

    public void addPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}