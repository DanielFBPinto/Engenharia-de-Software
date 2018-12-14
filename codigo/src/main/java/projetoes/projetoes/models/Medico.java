package projetoes.projetoes.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import projetoes.projetoes.jsonfiles.ConsultaJSON;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico extends Funcionario {
    private Integer cedulaMedica;
    private String especialidade;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Consulta> myConsulta = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Horario> myHorarioMedico = new HashSet<>();

    public String getName() {
        return super.getName();
    }

    public Medico(Integer cedulaMedica, String especialidade, String name) {
        super(name);
        this.cedulaMedica = cedulaMedica;
        this.especialidade = especialidade;
    }

    public void addConsulta(Consulta consulta) {
        this.myConsulta.add(consulta);
        consulta.addMedico(this);
    }

    public void addHorario(Horario horario) {
        this.myHorarioMedico.add(horario);
        horario.addMedico(this);
    }

    public boolean isWorking(LocalDateTime data) {
        for (Horario horario : this.myHorarioMedico) {
            if ((horario.getDiaSemana().equals(data.getDayOfWeek().name())
                    && horario.getHoraInicio().isBefore(data)) &&
                    horario.getHoraFim().isAfter(data)) {
                return true;
            }
        }
        return false;
    }

    public boolean temConsulta(LocalDateTime data) {
        for (Consulta consulta : this.myConsulta) {
            if (consulta.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPossible(LocalDateTime data) {
        if (this.isWorking(data)) {
            System.out.println("medicos is working");
            if (!this.temConsulta(data)) {
                System.out.println("medicos does not have an appointment");

                return true;
            }
        }
        return false;
    }

    public void marcarConsulta(Paciente paciente, LocalDateTime dataConsulta) {
        if (paciente.isFree(dataConsulta)) {
            if (this.isPossible(dataConsulta)) {
                Consulta consulta = new Consulta();
                paciente.addConsulta(consulta);
                this.addConsulta(consulta);
            }
        }
    }
    public boolean marcarConsulta(Paciente paciente, Consulta consulta) {
        if (paciente.isFree(consulta.getData())) {
            System.out.println("paciente is free");
            if (this.isPossible(consulta.getData())) {

                paciente.addConsulta(consulta);
                this.addConsulta(consulta);
                return true;
            }
        }
        return false;
    }

    public void cancelarConsulta(LocalDateTime dataConsulta, Paciente paciente) {
        for (Consulta consulta : this.myConsulta) {
            if (consulta.getData().equals(dataConsulta) && consulta.getMyPaciente().getId().equals(paciente.getId())) {
                paciente.getMyConsulta().remove(consulta);
                this.myConsulta.remove(consulta);
            }
        }
    }

    public void alterarConsulta(Paciente paciente, LocalDateTime dataAnterior, LocalDateTime novaData) {
        for (Consulta consulta : this.myConsulta) {
            if (consulta.getData().equals(dataAnterior)) {
                paciente.getMyConsulta().remove(consulta);
                this.myConsulta.remove(consulta);
                this.marcarConsulta(paciente, novaData);
            }
        }
    }

    public Consulta existeConsulta(Paciente paciente, LocalDateTime data) {
        for (Consulta consulta : this.myConsulta) {
            if (consulta.getData().equals(data) && consulta.getMyPaciente().getId().equals(paciente.getId())) {
                return consulta;
            }
        }
        return null;
    }
}

