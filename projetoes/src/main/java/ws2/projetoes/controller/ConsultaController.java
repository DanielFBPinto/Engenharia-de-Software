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
@RequestMapping(value = "/consulta")
public class ConsultaController{
    @Autowired
    private ClinicaService clinicaService;

//    @RequestMapping(value ="/clinica/{id}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Consulta> getAllConsultas(@PathVariable("id") Long id) {
//        System.out.println(id);
//        Clinica clinica = clinicaService.findById(id);
//        if(clinica != null) {
//            System.out.println(clinica.getUrl());
//            String path = clinica.getUrl().concat("consulta/");
//            //  String path="http://localhost:8090/consulta/marcarconsulta";
//            HttpHeaders headers = new HttpHeaders();
//            HttpEntity<Long> body = new HttpEntity<>(id, headers);
//
//            ResponseEntity<Consulta> responseEntity = makeRequest(path, HttpMethod.GET, body, Consulta.class);
//            return (responseEntity.getStatusCodeValue() == 200)
//                    ? ResponseEntity.ok(responseEntity.getBody())
//                    : ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta[]> getAllConsultas(@RequestParam("clinica") Long id) {
        // @RequestParam significa que no teu URL vais escrever, por exemplo, /?clinica=3
        // System.out.println(id);
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null) {
            //  System.out.println(clinica.getUrl());
            String path = clinica.getUrl().concat("consulta/");
            //  String path="http://localhost:8090/consulta/marcarconsulta";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id, headers);

            ResponseEntity<Consulta[]> responseEntity = makeRequest(path, HttpMethod.GET, body, Consulta[].class);
            return (responseEntity.getStatusCodeValue() == 200)
                    ? ResponseEntity.ok(responseEntity.getBody())
                    : ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
    @RequestMapping(value = "/byid",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> getConsultaById(@RequestParam("clinica") Long id,@RequestParam("id") Long id2) {
        // @RequestParam significa que no teu URL vais escrever, por exemplo, /?clinica=3
        // System.out.println(id);
        Clinica clinica = clinicaService.findById(id);
        if(clinica != null) {
            //  System.out.println(clinica.getUrl());
            String path = clinica.getUrl().concat("consulta/"+id2);
            //  String path="http://localhost:8090/consulta/marcarconsulta";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Long> body = new HttpEntity<>(id, headers);

            ResponseEntity<Consulta> responseEntity = makeRequest(path, HttpMethod.GET, body, Consulta.class);
            return (responseEntity.getStatusCodeValue() == 200)
                    ? ResponseEntity.ok(responseEntity.getBody())
                    : ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/marcarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> marcarConsulta(@RequestBody ConsultaJSON consultaJSON) {
        Clinica clinica = clinicaService.findById(consultaJSON.getIdClinica());
        String path=clinica.getUrl().concat("consulta/marcarconsulta");
        //  String path="http://localhost:8090/consulta/marcarconsulta";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ConsultaJSON> body=new HttpEntity<>(consultaJSON,headers);

        ResponseEntity<Consulta> responseEntity=makeRequest(path,HttpMethod.POST,body,Consulta.class);
        return (responseEntity.getStatusCodeValue()==200)
                ?ResponseEntity.ok(responseEntity.getBody())
                :ResponseEntity.notFound().build();
    }
    @RequestMapping(value = "/alterarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> alterarConsulta(@RequestBody ConsultaJSON consultaJSON) {
        Clinica clinica = clinicaService.findById(consultaJSON.getIdClinica());
        String path=clinica.getUrl().concat("consulta/alterarconsulta");
        //  String path="http://localhost:8090/consulta/marcarconsulta";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ConsultaJSON> body=new HttpEntity<>(consultaJSON,headers);

        ResponseEntity<Consulta> responseEntity=makeRequest(path,HttpMethod.POST,body,Consulta.class);
        return (responseEntity.getStatusCodeValue()==200)
                ?ResponseEntity.ok(responseEntity.getBody())
                :ResponseEntity.notFound().build();
    }
    @RequestMapping(value = "/cancelarconsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> cancelarConsulta(@RequestBody ConsultaJSON consultaJSON) {
        Clinica clinica = clinicaService.findById(consultaJSON.getIdClinica());
        String path=clinica.getUrl().concat("consulta/cancelarconsulta");
        //  String path="http://localhost:8090/consulta/marcarconsulta";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ConsultaJSON> body=new HttpEntity<>(consultaJSON,headers);
        ResponseEntity<Consulta> responseEntity=makeRequest(path,HttpMethod.POST,body,Consulta.class);
        return (responseEntity.getStatusCodeValue()==200)
                ?ResponseEntity.ok(responseEntity.getBody())
                :ResponseEntity.notFound().build();
    }
    private ResponseEntity makeRequest(String path, HttpMethod method, HttpEntity request, Class responseType){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}