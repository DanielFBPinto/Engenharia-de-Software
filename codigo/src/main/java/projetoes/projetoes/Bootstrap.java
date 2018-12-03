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
import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    private Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private MedicoRepoI medicoService;
    private ConsultaRepo consultaService;
    private PacienteRepo pacienteService;

    public Bootstrap(MedicoRepoI medicoService, ConsultaRepo consultaService, PacienteRepo pacienteService)

    {
        this.medicoService = medicoService;
        this.consultaService= consultaService;
        this.pacienteService= pacienteService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        Set<Medico> medicos = createMedicosFromFile();
        Set<Consulta> consultas= createConsultasFromFile(medicos);
        Set<Paciente> pacientes= createPacienteFromFile(consultas);
        for (Medico medico:medicos
             ) {
            medicoService.save(medico);
        }

        logger.debug(medicos.toString());
    }

    private Set<Medico> createMedicosFromFile()
    {
        Set<Medico> medicos = new HashSet<>();
        String line;

        InputStream is = this.getClass().getResourceAsStream("/medicos.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)))
        {
            while((line = br.readLine()) != null)
            {
                System.out.println(line);
                String attributes[]=line.split(",");

                Medico medico = new Medico(Integer.parseInt(attributes[0]), attributes[1],attributes[2]);
                medicos.add(medico);
                medicoService.save(medico);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicos;
    }
    private Set<Consulta> createConsultasFromFile(Set<Medico> medicos)
    {
        Set<Consulta> consultas = new HashSet<>();
        String line;

        InputStream is = this.getClass().getResourceAsStream("/consultas.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)))
        {
            while((line = br.readLine()) != null)
            {
                System.out.println(line);
                String attributes[]=line.split(",");

                Consulta consulta = new Consulta(Integer.parseInt(attributes[0]));
               Medico medico= getMById(Integer.parseInt(attributes[1]),medicos);
                if(medico!= null)
                    medico.addConsulta(consulta);
                consultas.add(consulta);
                consultaService.save(consulta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return consultas;
    }
    private Set<Paciente> createPacienteFromFile(Set<Consulta> consultas)
    {
        Set<Paciente> pacientes = new HashSet<>();
        String line;

        InputStream is = this.getClass().getResourceAsStream("/pacientes.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)))
        {
            while((line = br.readLine()) != null)
            {
                System.out.println(line);
                String attributes[]=line.split(",");

                Paciente paciente = new Paciente(attributes[0]);
                Consulta consulta;

                if(attributes.length>2) {
                    consulta = getCById(Integer.parseInt(attributes[1]), consultas);
                    if(consulta!=null)
                        consulta.addPaciente(paciente);
                }


                pacientes.add(paciente);
                pacienteService.save(paciente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }
    private Medico getMById(int id,Set<Medico>medicos){
        for(Medico medico: medicos){
            if(medico.getId()==(id))
                return medico;
        }
        return null;
    }
    private Consulta getCById(int id, Set<Consulta>consultas){
        for(Consulta consulta: consultas){
            if(consulta.getId()==id)
                return consulta;
        }

        return null;
    }

}
