package cepein.mapeamento.infra.service;

import cepein.mapeamento.infra.dto.CursoDto;
import cepein.mapeamento.infra.forms.CursoForms;
import cepein.mapeamento.model.Curso;
import cepein.mapeamento.infra.repository.CursoRepository;
import cepein.mapeamento.model.Pessoa;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Curso buscarCurso(Long idCurso){
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
    public void cadastrarCurso(CursoForms cursoForms){
        Pessoa pessoa = this.pessoaService.buscarPessoa(cursoForms.getPessoaId());
        Curso curso = cursoForms.converter(new Curso(), pessoa);
        this.cursoRepository.save(curso);


    }

    @Transactional
    public void alterarCurso(Long cursoId, CursoForms cursoForms){

        Curso curso = this.buscarCurso(cursoId);
        Pessoa pessoa = this.pessoaService.buscarPessoa(cursoForms.getPessoaId());

        Curso cursoAlterado = cursoForms.converter(curso, pessoa);
        this.cursoRepository.save(cursoAlterado);

    }

    @Transactional
    public void deletarCurso(Long idCurso){
        this.buscarCurso(idCurso);


        this.cursoRepository.deleteById(idCurso);

    }
}
