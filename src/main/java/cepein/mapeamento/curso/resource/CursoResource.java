package cepein.mapeamento.curso.resource;

import cepein.mapeamento.curso.dto.CursoDto;
import cepein.mapeamento.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
