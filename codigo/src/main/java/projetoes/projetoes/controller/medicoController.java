package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projetoes.projetoes.models.Consulta;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.repositories.MedicoRepoI;

@Controller
@RequestMapping("/Clinica")
public class medicoController
{
     @Autowired
     private MedicoRepoI medicoRepo;

     @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public @ResponseBody Iterable<Medico> getAllMedico()
     {
          return medicoRepo.findAll();
     }

     @RequestMapping(value = "/{cedulaMedica}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public @ResponseBody Medico getMedico(@PathVariable("cedulaMedica") Integer cedulaMedica)
     {
          return medicoRepo.findByCedulaMedica(cedulaMedica).get();
     }

     @RequestMapping(value = "/{especialidade",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
     public @ResponseBody Medico getMedico(@PathVariable("especialidade") String especialidade)
     {
          return medicoRepo.findByEspecialidade(especialidade).get();
     }

}
