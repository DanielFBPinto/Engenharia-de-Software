package projetoes.projetoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projetoes.projetoes.jsonfiles.EspecialidadeJSON;
import projetoes.projetoes.models.Especialidade;
import projetoes.projetoes.service.EspecialidadeService;

@RestController
@RequestMapping(value = "/especialidade")
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
    @RequestMapping(value = "/criarespecialidade", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especialidade> criarEspecialidade(@RequestBody EspecialidadeJSON especialidadeJSON) {
        Especialidade consulta = especialidadeService.criarEspecialidade(especialidadeJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }
    @RequestMapping(value = "/removerespecialidade", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Especialidade> removerEspecialidade(@RequestBody EspecialidadeJSON especialidadeJSON) {
        Especialidade consulta = especialidadeService.eliminarEspecialidade(especialidadeJSON);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);
    }


}
