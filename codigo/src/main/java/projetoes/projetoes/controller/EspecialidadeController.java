package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projetoes.projetoes.models.Especialidade;
import projetoes.projetoes.service.EspecialidadeService;

@RestController
@RequestMapping(value = "/especialidade.txt")
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
