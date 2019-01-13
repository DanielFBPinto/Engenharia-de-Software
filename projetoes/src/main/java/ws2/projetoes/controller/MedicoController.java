package ws2.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ws2.projetoes.filter.FilterMedicoObject;
import ws2.projetoes.models.Clinica;
import ws2.projetoes.models.Medico;
import ws2.projetoes.service.ClinicaService;

@RestController
@RequestMapping(value = "/medico")
public class MedicoController
{
    @Autowired
    private ClinicaService clinicaService;

//NAO eliminar
   @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico[]> getAllMedicos(@RequestParam("clinica") Long id)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("medico/getf");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id,headers);
            ResponseEntity<Medico[]> responseEntity = makeRequest(path,HttpMethod.GET,body,Medico[].class);
            if(responseEntity.getStatusCodeValue() == 200)
            {
                return ResponseEntity.ok(responseEntity.getBody());
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Medico[]> getAllMedicos2(@RequestParam("id") Long id,@RequestBody FilterMedicoObject filterMedicoObject)
{
    Clinica clinica = clinicaService.findById(id);
    if(clinica != null)
    {
        String path = clinica.getUrl().concat("medico/getf");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<FilterMedicoObject> body = new HttpEntity<>(filterMedicoObject,headers);
        ResponseEntity<Medico[]> responseEntity = makeRequest(path,HttpMethod.POST,body,Medico[].class);
        return responseEntity;
    }
    return ResponseEntity.badRequest().body(null);
}

    @RequestMapping(value="/byid",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getMedicoByID(@RequestParam("clinica") Long id,@RequestParam("idmedico") Long id2)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("medico/" + id2);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id,headers);
            ResponseEntity<Medico> responseEntity = makeRequest(path,HttpMethod.GET,body,Medico.class);
            if(responseEntity.getStatusCodeValue() == 200)
            {
                return ResponseEntity.ok(responseEntity.getBody());
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value="/bycedula",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getMedicoByCedulaMedica(@RequestParam("clinica") Long id,@RequestParam("idcedula") Integer idCedula)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("medico/cedula/" + idCedula);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Integer> body = new HttpEntity<>(idCedula,headers);
            ResponseEntity<Medico> responseEntity = makeRequest(path,HttpMethod.GET,body,Medico.class);
            if(responseEntity.getStatusCodeValue() == 200)
            {
                return ResponseEntity.ok(responseEntity.getBody());
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity makeRequest(String path,HttpMethod method,HttpEntity request,Class responseType)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}
