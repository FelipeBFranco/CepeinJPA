package cepein.mapeamento.curso.resource;

import cepein.mapeamento.curso.dto.CursoDto;
import cepein.mapeamento.curso.forms.CursoForms;
import cepein.mapeamento.curso.service.CursoService;
import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.forms.EnderecoForms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoResource {

    private final CursoService cursoService;

    @Autowired
    public CursoResource(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CursoDto>> listarCursos(){
        return this.cursoService.listarCursos();
    }

    @GetMapping("procurar-por-id/{idCurso}")
    public ResponseEntity<CursoDto> listarCursoPorId(@PathVariable Long idCurso){
        return this.cursoService.procurarCurso(idCurso);
    }
    @PostMapping("cadastrar-curso")
    public ResponseEntity<Void> cadastraEndereco(@RequestBody CursoForms cursoForms){
        return this.cursoService.cadastrarCurso(cursoForms);
    }
    @PutMapping ("alterar-curso/{idCurso}")
    public ResponseEntity<Void> alterarCurso(@PathVariable Long idCurso,@RequestBody CursoForms cursoForms){
        return this.cursoService.alterarCurso(idCurso,cursoForms);
    }

    @DeleteMapping("deletar-curso/{idCurso}")
    public ResponseEntity<Void> deleterCurso(@PathVariable Long idCurso){
        return this.cursoService.deletarCurso(idCurso);
    }
}
