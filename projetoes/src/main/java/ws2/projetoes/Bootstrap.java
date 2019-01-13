package ws2.projetoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ws2.projetoes.models.Clinica;
import ws2.projetoes.repositories.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private ClinicaRepo clinicaRepo;


    public Bootstrap(ClinicaRepo clinicaRepo) {
        this.clinicaRepo = clinicaRepo;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createClinicas();


    }

    private void createClinicas() {
        Set<Clinica> clinicas = new HashSet<>();
        Clinica clinica = new Clinica("Porto", "8090");
        clinicas.add(clinica);
        clinicaRepo.save(clinica);
        Clinica clinica2 = new Clinica("Braga", "8091");
        clinicas.add(clinica2);
        clinicaRepo.save(clinica2);

    }

//    private void createClinicas() {
//        Set<Clinica> clinicas = new HashSet<>();
//        Clinica clinica = new Clinica("Braga", "8090");
//        clinicas.add(clinica);
//        clinicaRepo.save(clinica);
//    }

}

