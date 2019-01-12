package ws2.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ws2.projetoes.jsonfiles.PacienteJSON;
import ws2.projetoes.models.Clinica;
import ws2.projetoes.models.Consulta;
import ws2.projetoes.models.Paciente;
import ws2.projetoes.service.ClinicaService;
@RestController
@RequestMapping(value = "/paciente")
public class PacienteController
{
    @Autowired
    private ClinicaService clinicaService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente[]> getAllPacientes(@RequestParam("clinica") Long id)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("paciente/");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id,headers);
            ResponseEntity<Paciente[]> responseEntity = makeRequest(path,HttpMethod.GET,body,Paciente[].class);
            System.out.println("oi");
            if(responseEntity.getStatusCode().is2xxSuccessful())
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
    public ResponseEntity<Paciente> getPacienteByID(@RequestParam ("clinica") Long id,@RequestParam("idPaciente") Long id2)
    {
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("paciente/" + id2);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id,headers);
            ResponseEntity<Paciente> responseEntity = makeRequest(path,HttpMethod.GET,body,Paciente.class);
            if(responseEntity.getStatusCode().is2xxSuccessful())
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
    @RequestMapping(value = "/registarpaciente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> registarPaciente(@RequestBody PacienteJSON pacienteJSON)
    {
        Clinica clinica = clinicaService.findById(pacienteJSON.getIdClinica());
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("paciente/registarpaciente");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<PacienteJSON> body = new HttpEntity<>(pacienteJSON, headers);
            ResponseEntity<Paciente> responseEntity = makeRequest(path, HttpMethod.POST, body, Paciente.class);
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

    @RequestMapping(value = "/alterarpaciente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> alterarpaciente(@RequestBody PacienteJSON pacienteJSON)
    {
        System.out.println("1");
        Clinica clinica = clinicaService.findById(pacienteJSON.getIdClinica());
        if(clinica != null)
        {
            String path = clinica.getUrl().concat("paciente/alterarpaciente");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<PacienteJSON> body = new HttpEntity<>(pacienteJSON, headers);
            System.out.println("2");
            ResponseEntity<Paciente> responseEntity = makeRequest(path, HttpMethod.POST, body, Paciente.class);
            System.out.println("3");
            if (responseEntity.getStatusCodeValue() == 200)
            {
                return ResponseEntity.ok(responseEntity.getBody());
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        }
        System.out.println("4");
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity makeRequest(String path,HttpMethod method,HttpEntity request,Class responseType)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}
