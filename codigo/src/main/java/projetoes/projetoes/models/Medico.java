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
@EqualsAndHashCode
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

    public Medico(Medico medico)
    {
<<<<<<< HEAD
        super(medico.getName(),medico.getDataNascimento(),medico.getDataNascimento());
=======
        super(medico.getName(),medico.getDataNascimento());
>>>>>>> master
        this.especialidade = medico.getEspecialidade();
    }

    public String getName() {
        return super.getName ();
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

    private boolean isWorking(LocalDateTime data) {
        for (Horario horario : this.myHorarioMedico) {
            if(horario.getDiaSemana().equals(data.getDayOfWeek().name())
               && horario.getHoraInicio().isBefore(data.toLocalTime())
               && horario.getHoraFim().isAfter(data.toLocalTime()))
            {
                return true;
            }
        }
        return false;
    }

    private boolean temConsulta(LocalDateTime data) {
        for (Consulta consulta : this.myConsulta) {
            if (consulta.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPossible(LocalDateTime data) {
        if (this.isWorking(data)) {
            if (!this.temConsulta(data)) {
                return true;
            }
        }
        return false;
    }

    public boolean marcarConsulta(Paciente paciente, LocalDateTime dataConsulta) {
        return this.marcarConsulta(paciente,new Consulta(dataConsulta));
        /*if (paciente.isFree(dataConsulta)) {
            if (this.isPossible(dataConsulta)) {
                Consulta consulta = new Consulta(dataConsulta);
                paciente.addConsulta(consulta);
                this.addConsulta(consulta);
            }
        }*/
    }
    public boolean marcarConsulta(Paciente paciente, Consulta consulta) {
        if (paciente.isFree(consulta.getData())) {
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

