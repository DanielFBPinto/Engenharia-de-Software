package projetoes.projetoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import projetoes.projetoes.models.*;
import projetoes.projetoes.repositories.*;


import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    private HorarioRepo horarioService;
    private MedicoRepoI medicoService;
    private ConsultaRepo consultaService;
    private PacienteRepo pacienteService;
    private AdministrativoRepo administrativoRepo;

    public Bootstrap(MedicoRepoI medicoService, ConsultaRepo consultaService, PacienteRepo pacienteService,
                     AdministrativoRepo administrativoRepo, HorarioRepo horarioRepo)

    {
        this.medicoService = medicoService;
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
        this.administrativoRepo = administrativoRepo;
        this.horarioService = horarioRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Set<Medico> medicos = createMedicosFromFile();
        Set<Horario> horarios = createHorarioFromFile(medicos);
        Set<Paciente> pacientes = createPacienteFromFile();
        Set<Consulta> consultas = createConsultasFromFile(medicos, pacientes);

        Administrativo administrativo = new Administrativo("Zequinha", "chefe");
        administrativoRepo.save(administrativo);
        //  for (Medico medico :medicos)
        // medicoService.save(medico);

        logger.debug(medicos.toString());
    }

    private Set<Medico> createMedicosFromFile() {
        Set<Medico> medicos = new HashSet<>();
        String line;

        InputStream is = this.getClass().getResourceAsStream("/medicos.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String attributes[] = line.split(",");
               // LocalDateTime diadenascimento = LocalDateTime.of((attributes[3]);

                Medico medico = new Medico(Integer.parseInt(attributes[0]), attributes[1], attributes[2]);
                medicos.add(medico);
                medicoService.save(medico);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    private Set<Horario> createHorarioFromFile(Set<Medico> medicos) {
        Set<Horario> horarios = new HashSet<>();
        String line;

        InputStream is = this.getClass().getResourceAsStream("/horarios.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String attributes[] = line.split(",");
                LocalTime horainicio = LocalTime.of(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1])
                        );
                LocalTime horafim = LocalTime.of(Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3])
                        );
                DayOfWeek dayOfWeek= DayOfWeek.of(Integer.parseInt(attributes[4]));
                Horario horario = new Horario(horainicio, horafim,dayOfWeek);
                Medico medico = getMById(Integer.parseInt(attributes[5]), medicos);
                if (medico != null)
                    medico.addHorario(horario);
                horarios.add(horario);
                horarioService.save(horario);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return horarios;
    }

    private Set<Consulta> createConsultasFromFile(Set<Medico> medicos, Set<Paciente> pacientes) {
        Set<Consulta> consultas = new HashSet<>();
        String line;

        InputStream is = this.getClass().getResourceAsStream("/consultas.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String attributes[] = line.split(",");
                LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]));
                Consulta consulta = new Consulta(localDateTime);
                Medico medico = getMById(Integer.parseInt(attributes[5]), medicos);
//                if (medico != null)
//                    medico.addConsulta(consulta);
                Paciente paciente = getPById(Integer.parseInt(attributes[6]), pacientes);
//                if (paciente != null)
//                    paciente.addConsulta(consulta);
                if(medico!=null && paciente!=null) {

                   if( medico.marcarConsulta(paciente, consulta)){
                       consultas.add(consulta);
                       consultaService.save(consulta);
                   }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    private Set<Paciente> createPacienteFromFile() {
        Set<Paciente> pacientes = new HashSet<>();
        String line;

        InputStream is = this.getClass().getResourceAsStream("/pacientes.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String attributes[] = line.split(",");

                Paciente paciente = new Paciente(attributes[0]);
//                Consulta consulta;
//
//                if(attributes.length>2) {
//                    consulta = getCById(Integer.parseInt(attributes[1]), consultas);
//                    if(consulta!=null)
//                        consulta.addPaciente(paciente);
//                }


                pacientes.add(paciente);
                pacienteService.save(paciente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    private Medico getMById(int id, Set<Medico> medicos) {
        for (Medico medico : medicos) {
            if (medico.getId() == (id))
                return medico;
        }
        return null;
    }

    private Paciente getPById(int id, Set<Paciente> pacientes) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id)
                return paciente;
        }

        return null;
    }

}

