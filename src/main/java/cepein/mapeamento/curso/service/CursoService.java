package cepein.mapeamento.curso.service;

import cepein.mapeamento.curso.dto.CursoDto;
import cepein.mapeamento.curso.forms.CursoForms;
import cepein.mapeamento.curso.model.Curso;
import cepein.mapeamento.curso.repository.CursoRepository;
import cepein.mapeamento.pedido.service.PedidoService;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import cepein.mapeamento.pessoa.service.PessoaService;
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

    private final PessoaService pessoaService;

    @Autowired
    public CursoService(CursoRepository cursoRepository,PessoaService pessoaService){
        this.cursoRepository = cursoRepository;
        this.pessoaService = pessoaService;
    }

    private Curso buscarCurso(Long idCurso){
        return this.cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado"));
    }

    public List<CursoDto> listarCursos(){
        List<Curso> cursoList = this.cursoRepository.findAll();
        List<CursoDto> cursoDtoList = CursoDto.converter(cursoList);

        return cursoDtoList;
    }

    public CursoDto procurarCurso(Long idCurso){
        Curso curso = this.buscarCurso(idCurso);
        CursoDto cursoDto = new CursoDto(curso);

        return cursoDto;
    }

    @Transactional
    public Void cadastrarCurso(CursoForms cursoForms){
        Pessoa pessoa = this.pessoaService.buscarPessoa(cursoForms.getPessoaId());
        Curso curso = cursoForms.converter(new Curso(), pessoa);
        this.cursoRepository.save(curso);

        return null;
    }

    @Transactional
    public Void alterarCurso(Long cursoId, CursoForms cursoForms){
        Curso curso = this.buscarCurso(cursoId);
        Pessoa pessoa = this.pessoaService.buscarPessoa(cursoForms.getPessoaId());

        Curso cursoAlterado = cursoForms.converter(curso, pessoa);
        this.cursoRepository.save(cursoAlterado);
        return null;
    }

    @Transactional
    public Void deletarCurso(Long idCurso){
        this.cursoRepository.deleteById(idCurso);

        return null;
    }
}
