package projetoes.projetoes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.repositories.HorarioRepo;

@Controller
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private HorarioRepo horarioRepo;

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Horario> getALLHorarios()
    {
        return horarioRepo.findAll();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Horario getHorarioById(@PathVariable("id")Long id) {
        return horarioRepo.findById(id).get();
    }
}
