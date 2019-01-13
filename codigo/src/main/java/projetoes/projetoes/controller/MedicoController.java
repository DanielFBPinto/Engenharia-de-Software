package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoes.projetoes.filters.FilterMedicoObject;
import projetoes.projetoes.filters.medicoFilters.MedicoFilter;
import projetoes.projetoes.filters.medicoFilters.MedicoFilterService;
import projetoes.projetoes.jsonfiles.ConsultaJSON;
import projetoes.projetoes.jsonfiles.MedicoJSON;
import projetoes.projetoes.models.Medico;
import projetoes.projetoes.service.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
//NAO ELIMINAR
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Medico>> getAllMedicos(@ModelAttribute FilterMedicoObject filterMedicoObject) {
        Iterable<Medico> medicos = medicoService.getFilteredMedicos(filterMedicoObject);
        return (medicos == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(medicos);
    }
    @RequestMapping(value = "/getf",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Medico>> getAllMedicos2(@ModelAttribute FilterMedicoObject filterMedicoObject) {
        Iterable<Medico> medicos = medicoService.getFilteredMedicos(filterMedicoObject);
        return (medicos == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(medicos);
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
    @RequestMapping(value = "/criarmedico", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> marcarConsulta(@RequestBody MedicoJSON consultaJSON) {
        Medico consulta = medicoService.criarMedico(consultaJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }

    @RequestMapping(value = "/editarmedico", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> alterarConsulta(@RequestBody MedicoJSON consultaJSON)
    {
        Medico consulta = medicoService.alterarMedico(consultaJSON);
        if (consulta == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }

    @RequestMapping(value = "/removermedico", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> removerMedico(@RequestBody MedicoJSON consultaJSON)
    {
        Medico consulta = medicoService.removerMedico(consultaJSON);
        if (consulta == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }
}
