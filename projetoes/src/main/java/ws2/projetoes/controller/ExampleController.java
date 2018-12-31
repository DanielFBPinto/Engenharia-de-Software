package ws2.projetoes.controller;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/especialidade")
public class ExampleController{
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable getAll(){
        String path="http://localhost:8090/especialidade";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> nullBodyRequest=new HttpEntity<>(null,headers);
        ResponseEntity<Iterable> responseEntity=makeRequest(path,HttpMethod.GET,nullBodyRequest,Iterable.class);
        return responseEntity.getBody();
    }

    private ResponseEntity makeRequest(String path, HttpMethod method, HttpEntity request, Class responseType){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.exchange(path,method,request,responseType);
    }
}