package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projetoes.projetoes.models.Paciente;
import projetoes.projetoes.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController
{
    @Autowired
    private PacienteService pacienteService;

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable <Paciente>> getAllPacientes()
    {
        Iterable<Paciente> allPacientes = pacienteService.getAllPacientes();
        if(allPacientes ==  null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allPacientes);
    }
}

