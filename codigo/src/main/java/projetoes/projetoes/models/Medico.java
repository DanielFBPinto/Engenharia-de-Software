package projetoes.projetoes.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Medico extends Funcionario {
    private Integer cedulaMedica;

    @ManyToOne
    @JsonInclude
    private Especialidade especialidade;
    private String estado;//a exercer funções, ferias, baixa, despedido etc....

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "medico")
    private Set<Consulta> consultas = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "medico")
    private Set<Horario> horarios = new HashSet<>();

    public Medico(Medico medico)
    {
        super(medico.getName(),medico.getDataNascimento(),medico.getNumCC());
        this.especialidade = medico.getEspecialidade();
        this.cedulaMedica=medico.getCedulaMedica();
    }

    public String getName()
    {
        return super.getName ();
    }

    public Medico(Integer cedulaMedica,Especialidade especialidade,String name) {
        super(name);
        this.cedulaMedica = cedulaMedica;
        this.especialidade = especialidade;
    }
    public Medico(Integer cedulaMedica,String name) {
        super(name);
        this.cedulaMedica = cedulaMedica;
    }

    public void addConsulta(Consulta consulta) {
        this.consultas.add(consulta);
        consulta.addMedico(this);
    }

    public void addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.addMedico(this);
    }

    private boolean isWorking(LocalDateTime data) {
        for (Horario horario : this.horarios) {
           // System.out.println("H="+horario.getDiaSemana().getValue()+"  D="+data.getDayOfWeek().getValue());
           // System.out.println("d "+horario.getDiaSemana()+"  d2 "+data.getDayOfWeek());
            if(horario.getDiaSemana().getValue()== data.getDayOfWeek().getValue()
               && horario.getHoraInicio().isBefore(data.toLocalTime())
               && horario.getHoraFim().isAfter(data.toLocalTime()))
            {
               // System.out.println("tem horario");
                return true;
            }
        }
        //System.out.println("nao tem horario");
        return false;
    }

    private boolean temConsulta(LocalDateTime data) {
        for (Consulta consulta : this.consultas) {
            System.out.println(consulta.getDia().getValue()+"?"+data.getDayOfWeek().getValue());
            System.out.println(consulta.getData()+"?"+data);
            System.out.println(consulta.getDatafim()+"?"+data);


            if (consulta.getDia().getValue()==data.getDayOfWeek().getValue()
                    && ((consulta.getData().isBefore(data)
                    && consulta.getDatafim().isAfter(data))
                    || (consulta.getData().isEqual(data)
                    || consulta.getDatafim().isEqual(data)))) {

                return true;
            }
        }
        return false;
    }

    public boolean isPossible(LocalDateTime data) {
        if (this.isWorking(data)) {
            if (!this.temConsulta(data)) {

               System.out.println("is possible true");
                return true;
            }
        }
        System.out.println("is possible falso");
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
        for (Consulta consulta : this.consultas) {
            if (consulta.getData().equals(dataConsulta) && consulta.getPaciente().getId().equals(paciente.getId())) {
                paciente.getConsultas().remove(consulta);
                this.consultas.remove(consulta);
            }
        }
    }
    public void cancelarConsulta(Consulta consulta,Paciente paciente) {
                paciente.getConsultas().remove(consulta);
                this.consultas.remove(consulta);
            }


    public void alterarConsulta(Paciente paciente, LocalDateTime dataAnterior, LocalDateTime novaData) {
        for (Consulta consulta : this.consultas) {
            if (consulta.getData().equals(dataAnterior)) {
                paciente.getConsultas().remove(consulta);
                this.consultas.remove(consulta);
                this.marcarConsulta(paciente, novaData);
            }
        }
    }

    public Consulta existeConsulta(Paciente paciente, LocalDateTime data) {
        for (Consulta consulta : this.consultas) {
            if (consulta.getData().equals(data) && consulta.getPaciente().getId().equals(paciente.getId())) {
                return consulta;
            }
        }
        return null;
    }
}

