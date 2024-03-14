package cepein.mapeamento.curso.resource;

import cepein.mapeamento.curso.dto.CursoDto;
import cepein.mapeamento.curso.forms.CursoForms;
import cepein.mapeamento.curso.service.CursoService;
import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.forms.EnderecoForms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(this.cursoService.listarCursos());
    }

    @GetMapping("/procurar-por-id/{idCurso}")
    public ResponseEntity<CursoDto> listarCursoPorId(@PathVariable Long idCurso){
        return ResponseEntity.ok(this.cursoService.procurarCurso(idCurso));
    }
    @PostMapping("/cadastrar-curso")
    public ResponseEntity<HttpStatus> cadastraEndereco(@RequestBody CursoForms cursoForms){
        this.cursoService.cadastrarCurso(cursoForms);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping ("/alterar-curso/{idCurso}")
    public ResponseEntity<HttpStatus> alterarCurso(@PathVariable Long idCurso,@RequestBody CursoForms cursoForms){
        this.cursoService.alterarCurso(idCurso,cursoForms);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar-curso/{idCurso}")
    public ResponseEntity<HttpStatus> deleterCurso(@PathVariable Long idCurso){
        this.cursoService.deletarCurso(idCurso);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
