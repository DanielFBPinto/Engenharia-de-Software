package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.service.MedicoService;


@RestController
@RequestMapping("/medico")
public class medicoController {
    @Autowired
    private MedicoService medicoService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Medico>> getAllMedicos() {
        Iterable<Medico> allMedicos = medicoService.getAllMedicos();
        if (allMedicos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allMedicos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getMedicoById(@PathVariable("id") Long id) {
        Medico medico = medicoService.findById(id);
        if (medico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medico);
    }

    @RequestMapping(value = "/cedula/{cedulaMedica}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getMedicoCedula(@PathVariable("cedulaMedica") Integer cedulaMedica) {
        Medico medico = medicoService.findByCedulaMedica(cedulaMedica);
        if (medico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medico);
    }


    @RequestMapping(value = "/especialidade/{especialidade}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getMedicoEspecialidade(@PathVariable("especialidade") String especialidade) {
        Medico medico = medicoService.findByEspecialidade(especialidade);
        if (medico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medico);
    }
}
