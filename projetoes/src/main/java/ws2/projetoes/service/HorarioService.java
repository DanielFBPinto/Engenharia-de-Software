package ws2.projetoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws2.projetoes.jsonfiles.HorarioJSON;
import ws2.projetoes.models.Consulta;
import ws2.projetoes.models.Horario;
import ws2.projetoes.models.Medico;
import ws2.projetoes.repositories.ConsultaRepo;
import ws2.projetoes.repositories.HorarioRepo;
import ws2.projetoes.repositories.MedicoRepoI;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepo horarioRepo;
    @Autowired
    private MedicoRepoI medicoRepo;
    @Autowired
    private ConsultaRepo consultaRepo;


    public Iterable<Horario> getAllHorarios() {
        return horarioRepo.findAll();
    }

    public Horario findById(long id) {
        if (horarioRepo.findById(id).isPresent()) {
            return horarioRepo.findById(id).get();
        }
        return null;
    }

    /**
     * Vai alterar o horario de um dia caso exista
     * caso contrario apenas vai criar um novo
     *
     * @param horarioJSON
     * @return
     */
    public Horario alterarhorario(HorarioJSON horarioJSON) {
        Medico medico = medicoRepo.findById(horarioJSON.getMedico()).get();
        if (medico != null) {
            Horario newHorario = new Horario(horarioJSON.getHoraInicio(), horarioJSON.getHoraFim(), horarioJSON.getDiaSemana());
            Horario horario = existeHorario(medico, horarioJSON);
            if (horario != null) {
                this.manterConsultas(medico, newHorario);
                this.cancelarHorario(horario, medico);
                return this.marcarHorario(newHorario, medico);
            }

            return this.marcarHorario(newHorario, medico);
        }
        return null;
    }


    /**
     * Vai eliminar as consultas que não respeitam o novo horario
     *
     * @param medico
     * @param horario
     */
    public void manterConsultas(Medico medico, Horario horario) {
        for (Consulta consulta : medico.getConsultas()) {
            if (consulta.getDia() == horario.getDiaSemana()) {
                if (consulta.getData().toLocalTime().isBefore(horario.getHoraInicio()) ||
                        consulta.getData().toLocalTime().isAfter(horario.getHoraFim())) {
                    medico.getConsultas().remove(consulta);
                    consultaRepo.delete(consulta);
                }

            }
        }
    }

    public Horario existeHorario(Medico medico, HorarioJSON horarioJSON) {
        for (Horario horario : medico.getHorarios()) {
            if (horario.getDiaSemana().equals(horarioJSON.getDiaSemana())) {
                return horario;
            }
        }
        return null;
    }

    /**
     * Vai criar um horario e chamar a funçao marcarHorario(Horario,Medico)
     *
     * @param horarioJSON
     * @return
     */
    public Horario marcarHorario(HorarioJSON horarioJSON) {
        Medico medico = medicoRepo.findById(horarioJSON.getMedico()).get();
        Horario horario = existeHorario(medico, horarioJSON);
        if (horario == null) {
            Horario newhorario = new Horario(horarioJSON.getHoraInicio(), horarioJSON.getHoraFim(), horarioJSON.getDiaSemana());

            return marcarHorario(newhorario, medico);
        }
        return null;
    }

    /**
     * Vai guardar um horario no medico e na base de dados
     *
     * @param horario
     * @param medico
     * @return
     */
    public Horario marcarHorario(Horario horario, Medico medico) {
        medico.addHorario(horario);
        horarioRepo.save(horario);
        return horario;
    }

    /**
     * Vai procurar um medico
     * Se encontrar um horario com o dia enviado vai chamar a funçaoc cancelarHorarioRIPconsultas(Horario, Medico)
     *
     * @param horarioJSON
     * @return
     */
    public Horario cancelarHorario(HorarioJSON horarioJSON) {
        Medico medico = medicoRepo.findById(horarioJSON.getMedico()).get();
        if (medico != null) {
            Horario horario = existeHorario(medico, horarioJSON);
            if (horario != null) {
                return cancelarHorarioRIPconsultas(horario, medico);
            }
        }

        return null;
    }

    /**
     * Vai eliminar as todas as consultas que o médico tem naquele dia
     * tanto do médico como da base de dados
     * vai chamar a funçao cancelarHorario(Horario, Medico)
     *
     * @param horario
     * @param medico
     * @return
     */
    public Horario cancelarHorarioRIPconsultas(Horario horario, Medico medico) {
        for (Consulta consulta : medico.getConsultas()) {
            if (consulta.getDia().equals(horario.getDiaSemana())) {
                medico.getConsultas().remove(consulta);
                consultaRepo.delete(consulta);
            }
        }
        //  this.manterConsultas(medico,horario);
        return this.cancelarHorario(horario, medico);
    }

    /**
     * Elimina um horario do médico e da base de dados
     *
     * @param horario
     * @param medico
     * @return
     */
    public Horario cancelarHorario(Horario horario, Medico medico) {
        medico.getHorarios().remove(horario);
        horarioRepo.delete(horario);
        return horario;
    }

}
