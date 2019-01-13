package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projetoes.projetoes.jsonfiles.PacienteJSON;
import projetoes.projetoes.models.Paciente;
import projetoes.projetoes.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Paciente>> getAllPacientes() {
        Iterable<Paciente> allPacientes = pacienteService.getAllPacientes();
        if (allPacientes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allPacientes);
    }
    @RequestMapping(value = "/registarpaciente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> registarPaciente(@RequestBody PacienteJSON pacienteJSON) {
        Paciente paciente = pacienteService.registar(pacienteJSON);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @RequestMapping(value = "/alterarpaciente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> alterarConsulta(@RequestBody PacienteJSON pacienteJSON) {
        Paciente paciente = pacienteService.alterarPaciente(pacienteJSON);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }
}

