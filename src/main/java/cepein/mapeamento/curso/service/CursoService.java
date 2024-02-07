package cepein.mapeamento.curso.service;

import cepein.mapeamento.curso.dto.CursoDto;
import cepein.mapeamento.curso.forms.CursoForms;
import cepein.mapeamento.curso.model.Curso;
import cepein.mapeamento.curso.repository.CursoRepository;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository, PessoaRepository pessoaRepository){
        this.cursoRepository = cursoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    private Pessoa buscarPessoa(Long idPessoa){
        return this.pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrado"));
    }
    private Curso buscarCurso(Long idCurso){
        return this.cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado"));
    }

    public ResponseEntity<List<CursoDto>> listarCursos(){
        List<Curso> cursoList = this.cursoRepository.findAll();
        List<CursoDto> cursoDtoList = CursoDto.converter(cursoList);

        return ResponseEntity.ok(cursoDtoList);
    }

    public ResponseEntity<CursoDto> procurarCurso(Long idCurso){
        Curso curso = this.buscarCurso(idCurso);
        CursoDto cursoDto = new CursoDto(curso);

        return ResponseEntity.ok(cursoDto);
    }

    @Transactional
    public ResponseEntity<Void> cadastrarCurso(CursoForms cursoForms){
        Pessoa pessoa = this.buscarPessoa(cursoForms.getPessoaId());
        Curso curso = cursoForms.converter(new Curso(), pessoa);
        this.cursoRepository.save(curso);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<Void> alterarCurso(Long cursoId, CursoForms cursoForms){
        Curso curso = this.buscarCurso(cursoId);
        Pessoa pessoa = this.buscarPessoa(cursoForms.getPessoaId());

        Curso cursoAlterado = cursoForms.converter(curso, pessoa);
        this.cursoRepository.save(cursoAlterado);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<Void> deletarCurso(Long idCurso){
        this.cursoRepository.deleteById(idCurso);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
