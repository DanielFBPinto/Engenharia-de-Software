package ws2.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import ws2.projetoes.models.Clinica;

import ws2.projetoes.models.Especialidade;
import ws2.projetoes.service.ClinicaService;

@RestController
@RequestMapping(value = "/clinica")
public class ClinicaController
{
    @Autowired
    private ClinicaService clinicaService;

    @GetMapping(value = "/getEspecialidades",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Clinica>> getClinicaEspecialidades(){
        for(Clinica clinica : clinicaService.getAllClinicas()){
            String path= clinica.getUrl().concat("especialidade/");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> nullBodyrequest = new HttpEntity<>(null,headers);
            ResponseEntity<Especialidade[]>responseEntity= makeRequest(path, HttpMethod.GET,nullBodyrequest,Especialidade[].class);
            for(Especialidade especialidade: responseEntity.getBody()){
                clinica.adicionarEspecialidades(especialidade);
            }
            clinicaService.saveClinica(clinica);
        }
        return ResponseEntity.ok(clinicaService.getAllClinicas());

    }

    private ResponseEntity makeRequest(String path,HttpMethod method,HttpEntity request,Class responseType)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}
