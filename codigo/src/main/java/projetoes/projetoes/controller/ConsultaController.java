package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import projetoes.projetoes.models.Consulta;
import projetoes.projetoes.repositories.ConsultaRepo;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaRepo consultaRepo;

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Consulta> getALLConsultas(){
        return consultaRepo.findAll();
    }
}
