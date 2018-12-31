package ws2.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ws2.projetoes.models.Especialidade;
import ws2.projetoes.service.EspecialidadeService;

@RestController
//@RequestMapping(value = "/especialidade")
public class EspecialidadeController
{
    @Autowired
    private EspecialidadeService especialidadeService;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Especialidade>> getAllEspecialidade()
    {
        Iterable<Especialidade> allEspecialidade = especialidadeService.getAllEspecialidades();
        if (allEspecialidade == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allEspecialidade);
    }
}
