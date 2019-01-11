package ws2.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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

    private ResponseEntity makeRequest(String path,HttpMethod method,HttpEntity request,Class responseType)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}
