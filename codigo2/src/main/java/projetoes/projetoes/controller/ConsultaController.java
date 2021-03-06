package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoes.projetoes.jsonfiles.ConsultaJSON;
import projetoes.projetoes.models.Consulta;
import projetoes.projetoes.service.ConsultaService;

import java.util.ArrayList;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Consulta>> getAllConsultas() {
        Iterable<Consulta> allConsultas = consultaService.getAllConsultas();
        if (allConsultas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allConsultas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> getById(@PathVariable("id") Long id) {
        Consulta consulta = consultaService.findById(id);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }
    @RequestMapping(value = "paciente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Consulta>> getallpacientes(@PathVariable("id") Long id) {
        Iterable<Consulta> allConsultas = consultaService.getAllConsultas();
        ArrayList<Consulta> consultas = new ArrayList<>();

        for (Consulta consulta:allConsultas
                ) {
            if(id.equals(consulta.getPaciente().getId()))
                consultas.add(consulta);
        }
        if (consultas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consultas);
    }
    @RequestMapping(value = "medico/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Consulta>> getallmedicos(@PathVariable("id") Long id) {
        Iterable<Consulta> allConsultas = consultaService.getAllConsultas();
        ArrayList<Consulta> consultas = new ArrayList<>();

        for (Consulta consulta:allConsultas
                ) {
            if(id.equals(consulta.getMedico().getId()))
                consultas.add(consulta);
        }
        if (consultas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consultas);
    }
    @RequestMapping(value = "/marcarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> marcarConsulta(@RequestBody ConsultaJSON consultaJSON) {
        Consulta consulta = consultaService.marcarConsulta(consultaJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }

    @RequestMapping(value = "/alterarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> alterarConsulta(@RequestBody ConsultaJSON consultaJSON) {
        Consulta consulta = consultaService.alterarConsulta(consultaJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }

    @RequestMapping(value = "/cancelarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> cancelarConsulta(@RequestBody ConsultaJSON consultaJSON) {
        Consulta consulta = consultaService.cancelarConsulta(consultaJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }
}
