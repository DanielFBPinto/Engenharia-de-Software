package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoes.projetoes.jsonfiles.HorarioJSON;
import projetoes.projetoes.models.Horario;
import projetoes.projetoes.service.HorarioService;

@RestController
@RequestMapping("/horario")
public class HorarioController
{
    @Autowired
    private HorarioService horarioService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Horario>> getAllHorarios() {
        Iterable<Horario> allHorarios = horarioService.getAllHorarios();
        if (allHorarios == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allHorarios);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Horario> getById(@PathVariable("id") Long id) {
        Horario horario = horarioService.findById(id);
        if (horario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horario);
    }

    @RequestMapping(value = "/alterarhorario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Horario> alterarConsulta(@RequestBody HorarioJSON consultaJSON) {
        Horario consulta = horarioService.alterarhorario(consultaJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);


    }

    @RequestMapping(value = "/marcarhorario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Horario> marcarHorario(@RequestBody HorarioJSON horarioJSON) {
        Horario horario = horarioService.marcarHorario(horarioJSON);
        if (horario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(horario);
    }


    @RequestMapping(value = "/cancelarhorario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Horario> cancelarHorario(@RequestBody HorarioJSON horarioJSON) {
        Horario consulta = horarioService.cancelarHorario(horarioJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }
}