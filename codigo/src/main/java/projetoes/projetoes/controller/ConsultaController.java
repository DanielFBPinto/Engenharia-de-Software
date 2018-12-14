package projetoes.projetoes.controller;

import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoes.projetoes.jsonfiles.ConsultaJSON;
import projetoes.projetoes.models.Consulta;
import projetoes.projetoes.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController
{
    @Autowired
    private ConsultaService consultaService;

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Consulta>> getAllConsultas()
    {
        Iterable<Consulta> allConsultas = consultaService.getAllConsultas();
        if(allConsultas == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allConsultas);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta>getById(@PathVariable("id")Long id)
    {
        Consulta consulta = consultaService.findById(id);
        if(consulta == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }

    @RequestMapping(value = "/marcarconsulta",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> marcarConsulta(@ModelAttribute ConsultaJSON consultaJSON)
    {
        Consulta consulta = consultaService.marcarConsulta(consultaJSON);
        if(consulta == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }


}
