package projetoes.projetoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import projetoes.projetoes.models.Clinica.Medico;
import projetoes.projetoes.repositories.MedicoRepoI;


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

    public Bootstrap(MedicoRepoI medicoService)
    {
        this.medicoService = medicoService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        Set<Medico> medicos = createCoursesFromFile();
        logger.debug(medicos.toString());
    }

    private Set<Medico> createCoursesFromFile()
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

                Medico medico = new Medico(Integer.parseInt(attributes[0]), attributes[1]);
                medicos.add(medico);
                medicoService.save(medico);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicos;
    }
}

