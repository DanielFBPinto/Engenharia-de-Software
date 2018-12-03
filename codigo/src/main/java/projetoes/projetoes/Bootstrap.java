package projetoes.projetoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import projetoes.projetoes.models.*;
import projetoes.projetoes.repositories.*;


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
        Set<Consulta> consultas= createConsultasFromFile();
        Set<Paciente> pacientes= createPacienteFromFile();
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
    private Set<Consulta> createConsultasFromFile()
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
                consultas.add(consulta);
                consultaService.save(consulta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return consultas;
    }
    private Set<Paciente> createPacienteFromFile()
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
                pacientes.add(paciente);
                pacienteService.save(paciente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }


}

