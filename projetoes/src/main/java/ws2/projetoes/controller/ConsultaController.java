package ws2.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ws2.projetoes.jsonfiles.ConsultaJSON;
import ws2.projetoes.models.Clinica;
import ws2.projetoes.models.Consulta;
import ws2.projetoes.service.ClinicaService;

@RestController
@RequestMapping(value = "/consulta")
public class ConsultaController
{
    @Autowired
    private ClinicaService clinicaService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta[]> getAllConsultas(@RequestParam("clinica") Long id)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("consulta/");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id,headers);
            ResponseEntity<Consulta[]> responseEntity = makeRequest(path,HttpMethod.GET,body,Consulta[].class);
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

    @RequestMapping(value = "/byid",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> getConsultaById(@RequestParam("clinica") Long id,@RequestParam("id") Long id2)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("consulta/" + id2);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id, headers);
            ResponseEntity<Consulta> responseEntity = makeRequest(path, HttpMethod.GET, body, Consulta.class);
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
    @RequestMapping(value ="/bymedico" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta[]> getAllmedicos(@RequestParam("clinica") Long id,@RequestParam("id") Long id2)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("consulta/medico/"+id2);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id,headers);
            ResponseEntity<Consulta[]> responseEntity = makeRequest(path,HttpMethod.GET,body,Consulta[].class);
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
    @RequestMapping(value ="/bypaciente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta[]> getAllpaciente(@RequestParam("clinica") Long id,@RequestParam("id") Long id2)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("consulta/paciente/"+id2);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id,headers);
            ResponseEntity<Consulta[]> responseEntity = makeRequest(path,HttpMethod.GET,body,Consulta[].class);
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
    @RequestMapping(value = "/marcarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> marcarConsulta(@RequestBody ConsultaJSON consultaJSON)
    {
        Clinica clinica = clinicaService.findById(consultaJSON.getIdClinica());
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("consulta/marcarconsulta");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<ConsultaJSON> body = new HttpEntity<>(consultaJSON, headers);
            ResponseEntity<Consulta> responseEntity = makeRequest(path, HttpMethod.POST, body, Consulta.class);
            if (responseEntity.getStatusCodeValue() == 200)
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

    @RequestMapping(value = "/alterarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> alterarConsulta(@RequestBody ConsultaJSON consultaJSON)
    {
        Clinica clinica = clinicaService.findById(consultaJSON.getIdClinica());
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("consulta/alterarconsulta");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<ConsultaJSON> body = new HttpEntity<>(consultaJSON, headers);
            ResponseEntity<Consulta> responseEntity = makeRequest(path,HttpMethod.POST,body,Consulta.class);
            if (responseEntity.getStatusCodeValue() == 200)
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

    @RequestMapping(value = "/cancelarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> cancelarConsulta(@RequestBody ConsultaJSON consultaJSON)
    {
        Clinica clinica = clinicaService.findById(consultaJSON.getIdClinica());
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("consulta/cancelarconsulta");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<ConsultaJSON> body = new HttpEntity<>(consultaJSON, headers);
            ResponseEntity<Consulta> responseEntity = makeRequest(path, HttpMethod.POST,body,Consulta.class);
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

    private ResponseEntity makeRequest(String path, HttpMethod method, HttpEntity request, Class responseType)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}