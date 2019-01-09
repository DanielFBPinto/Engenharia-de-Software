package ws2.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ws2.projetoes.jsonfiles.ConsultaJSON;
import ws2.projetoes.models.Clinica;
import ws2.projetoes.models.Consulta;
import ws2.projetoes.service.ClinicaService;

@RestController
@RequestMapping(value = "/clinica")
public class ExampleController{
//    @Autowired
//   private ClinicaService clinicaService;
//    @RequestMapping(value = "/marcarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Consulta> marcarConsulta(@RequestBody ConsultaJSON consultaJSON) {
//        Clinica clinica = clinicaService.findById(consultaJSON.getIdClinica());
//        String path=clinica.getUrl().concat("consulta/marcarconsulta");
//      //  String path="http://localhost:8090/consulta/marcarconsulta";
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<ConsultaJSON> body=new HttpEntity<>(consultaJSON,headers);
//
//        ResponseEntity<Consulta> responseEntity=makeRequest(path,HttpMethod.POST,body,Consulta.class);
//        return (responseEntity.getStatusCodeValue()==200)
//                ?ResponseEntity.ok(responseEntity.getBody())
//                :ResponseEntity.notFound().build();
//    }

    private ResponseEntity makeRequest(String path, HttpMethod method, HttpEntity request, Class responseType){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}