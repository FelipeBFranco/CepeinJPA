package cepein.mapeamento.curso.service;

import cepein.mapeamento.curso.dto.CursoDto;
import cepein.mapeamento.curso.model.Curso;
import cepein.mapeamento.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public ResponseEntity<List<CursoDto>> listarCursos(){
        List<Curso> cursoList = this.cursoRepository.findAll();
        List<CursoDto> cursoDtoList = CursoDto.converter(cursoList);

        return ResponseEntity.ok(cursoDtoList);
    }
}
