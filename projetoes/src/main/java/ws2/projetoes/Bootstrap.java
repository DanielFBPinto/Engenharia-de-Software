package ws2.projetoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ws2.projetoes.models.Clinica;
import ws2.projetoes.models.Especialidade;
import ws2.projetoes.repositories.*;

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
//    private HorarioRepo horarioService;
  private EspecialidadeRepo especialidadeService;
  private ClinicaRepo clinicaRepo;
//    private MedicoRepoI medicoService;
//    private ConsultaRepo consultaService;
//    private PacienteRepo pacienteService;
//    private AdministrativoRepo administrativoRepo;

    public Bootstrap(ClinicaRepo clinicaRepo)
    {
          this.clinicaRepo=clinicaRepo;
//        this.medicoService = medicoService;
//        this.consultaService = consultaService;
//        this.pacienteService = pacienteService;
//        this.administrativoRepo = administrativoRepo;
//        this.horarioService = horarioRepo;
     // this.especialidadeService= especialidadeService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
      Set<Clinica> clinicas= createClinicas();
          //Set<Especialidade> especialidades= ;
//        Set<Medico> medicos = createMedicosFromFile(especialidades);
//        Set<Horario> horarios = createHorarioFromFile(medicos);
//        Set<Paciente> pacientes = createPacienteFromFile();
//        Set<Consulta> consultas = createConsultasFromFile(medicos, pacientes);
//
//        Administrativo administrativo = new Administrativo("Zequinha", "chefe");
//        administrativoRepo.save(administrativo);
        //  for (Medico medico :medicos)
        // medicoService.save(medico);

       // logger.debug(medicos.toString());
    }
    private Set<Clinica> createClinicas(){
      Set<Clinica> clinicas = new HashSet<>();
      Clinica clinica= new Clinica("Porto","8090");
      clinicas.add(clinica);
      clinicaRepo.save(clinica);
      return clinicas;

    }
//    private Set<Especialidade> createEspacialidadesFromFile() {
//        Set<Especialidade> especialidades = new HashSet<>();
//        String line;
//
//        InputStream is = this.getClass().getResourceAsStream("/especialidade.txt");
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//                String attributes[] = line.split(",");
//                // LocalDateTime diadenascimento = LocalDateTime.of((attributes[3]);
//                Especialidade especialidade = new Especialidade(attributes[0]);
//              //  Medico medico = new Medico(Integer.parseInt(attributes[0]), especialidade, attributes[2]);
//                especialidades.add(especialidade);
//                especialidadeService.save(especialidade);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return especialidades;
//    }
//    private Set<Medico> createMedicosFromFile(Set<Especialidade> especialidades) {
//        Set<Medico> medicos = new HashSet<>();
//        String line;
//
//        InputStream is = this.getClass().getResourceAsStream("/medicos.txt");
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//                String attributes[] = line.split(",");
//               // LocalDateTime diadenascimento = LocalDateTime.of((attributes[3]);
//                Medico medico = new Medico(Integer.parseInt(attributes[0]),attributes[2]);
//                Especialidade especialidade = getEByname(attributes[1],especialidades);
//                if(especialidade!=null)
//                    medico.setEspecialidade(especialidade);
//                medicos.add(medico);
//                medicoService.save(medico);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return medicos;
//    }
//
//    private Set<Horario> createHorarioFromFile(Set<Medico> medicos) {
//        Set<Horario> horarios = new HashSet<>();
//        String line;
//
//        InputStream is = this.getClass().getResourceAsStream("/horarios.txt");
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//                String attributes[] = line.split(",");
//                LocalTime horainicio = LocalTime.of(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1])
//                        );
//                LocalTime horafim = LocalTime.of(Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3])
//                        );
//                DayOfWeek dayOfWeek= DayOfWeek.of(Integer.parseInt(attributes[4]));
//              //  System.out.println("Day of week="+dayOfWeek);
//                Horario horario = new Horario(horainicio, horafim,dayOfWeek);
//                Medico medico = getMById(Integer.parseInt(attributes[5]), medicos);
//                if (medico != null)
//                    medico.addHorario(horario);
//                horarios.add(horario);
//                horarioService.save(horario);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return horarios;
//    }
//
//    private Set<Consulta> createConsultasFromFile(Set<Medico> medicos, Set<Paciente> pacientes) {
//        Set<Consulta> consultas = new HashSet<>();
//        String line;
//
//        InputStream is = this.getClass().getResourceAsStream("/consultas.txt");
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//                String attributes[] = line.split(",");
//                LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]));
//                Consulta consulta = new Consulta(localDateTime);
//                Medico medico = getMById(Integer.parseInt(attributes[5]), medicos);
////                if (medico != null)
////                    medico.addConsulta(consulta);
//                Paciente paciente = getPById(Integer.parseInt(attributes[6]), pacientes);
////                if (paciente != null)
////                    paciente.addConsulta(consulta);
//                if(medico!=null && paciente!=null) {
//
//                   if( medico.marcarConsulta(paciente, consulta)){
//                       consultas.add(consulta);
//                       consultaService.save(consulta);
//                   }
//
//                }
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return consultas;
//    }
//
//    private Set<Paciente> createPacienteFromFile() {
//        Set<Paciente> pacientes = new HashSet<>();
//        String line;
//
//        InputStream is = this.getClass().getResourceAsStream("/pacientes.txt");
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//                String attributes[] = line.split(",");
//
//                Paciente paciente = new Paciente(attributes[0]);
////                Consulta consulta;
////
////                if(attributes.length>2) {
////                    consulta = getCById(Integer.parseInt(attributes[1]), consultas);
////                    if(consulta!=null)
////                        consulta.addPaciente(paciente);
////                }
//
//
//                pacientes.add(paciente);
//                pacienteService.save(paciente);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return pacientes;
//    }
//
//    private Medico getMById(int id, Set<Medico> medicos) {
//        for (Medico medico : medicos) {
//            if (medico.getId() == (id))
//                return medico;
//        }
//        return null;
//    }
//
//    private Paciente getPById(int id, Set<Paciente> pacientes) {
//        for (Paciente paciente : pacientes) {
//            if (paciente.getId() == id)
//                return paciente;
//        }
//
//        return null;
//    }
//    private Especialidade getEByname(String name, Set<Especialidade> especialidades) {
//        for (Especialidade especialidade : especialidades) {
//            if (especialidade.getNomeEspecialidade().compareTo(name)==0)
//                return especialidade;
//        }
//
//        return null;
//    }

}

