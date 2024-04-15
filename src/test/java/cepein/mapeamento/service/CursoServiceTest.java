package cepein.mapeamento.service;

import cepein.mapeamento.infra.dto.CursoDto;
import cepein.mapeamento.infra.forms.CursoForms;
import cepein.mapeamento.infra.service.CursoService;
import cepein.mapeamento.infra.service.PessoaService;
import cepein.mapeamento.model.Curso;
import cepein.mapeamento.infra.repository.CursoRepository;
import cepein.mapeamento.infra.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.model.Pessoa;
import cepein.mapeamento.infra.repository.PessoaRepository;
import exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class CursoServiceTest {

    public static final Long ID = 1L;
    public static final String DESCRICAO = "Cobol do Vitão";
    public static final Long ID_PESSOA = 1L;
    public static final String UUID_PESSOA = "lhvhv2vu";
    public static final int INDEX = 0;
    public static final String CURSO_NAO_ENCONTRADO = "Curso não encontrado";
    public static final String PESSOA_NAO_ENCONTRADO = "Pessoa não encontrado";

    @Mock
    private CursoRepository cursoRepository;
    @InjectMocks
    private CursoService cursoService;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private PessoaService pessoaService;


    private Curso curso;
    private CursoDto cursoDto;
    private CursoForms cursoForms;
    private Optional<Curso> cursoOptional;
    private Optional<Pessoa> pessoaOptional;
    private Pessoa pessoa;
    private PessoaDtoParaRelacionamento pessoaDtoParaRelacionamento;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startCurso();
    }
    @Test
    void buscarCurso(){
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenReturn(cursoOptional);
        Curso response = cursoService.buscarCurso(ID);

        Assertions.assertEquals(Curso.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescricao());
        Assertions.assertEquals(pessoa.getId(), response.getPessoaPorId().getId());
        Assertions.assertEquals(pessoa.getUuid(), response.getPessoaPorUuid().getUuid());


    }
    @Test
    void buscarCursoExeptionObjectNotFound() {
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(CURSO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cursoService.buscarCurso(ID));
        Assertions.assertEquals(CURSO_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }
    @Test
    void listarCursos() {
        Mockito.when(cursoRepository.findAll()).thenReturn(List.of(curso));
        List<CursoDto> response = cursoService.listarCursos();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(CursoDto.class, response.get(INDEX).getClass());

        Assertions.assertEquals(cursoDto.getId(), response.get(INDEX).getId());
        Assertions.assertEquals(cursoDto.getDescricao(), response.get(INDEX).getDescricao());
        Assertions.assertEquals(cursoDto.getPessoaPorId().getId(), response.get(INDEX).getPessoaPorId().getId());
        Assertions.assertEquals(cursoDto.getPessoaPorUuid().getUuid(), response.get(INDEX).getPessoaPorUuid().getUuid());
    }

    @Test
    void procurarCurso() {
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenReturn(cursoOptional);
        CursoDto response = cursoService.procurarCurso(ID);

        Assertions.assertEquals(CursoDto.class, response.getClass());
        Assertions.assertEquals(cursoDto.getId(), response.getId());
        Assertions.assertEquals(cursoDto.getDescricao(), response.getDescricao());
        Assertions.assertEquals(cursoDto.getPessoaPorId().getId(), response.getPessoaPorId().getId());
        Assertions.assertEquals(cursoDto.getPessoaPorUuid().getUuid(), response.getPessoaPorUuid().getUuid());

    }
    @Test
    void procurarCursoExeptionObjectNotFound() {
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(CURSO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cursoService.procurarCurso(ID));
        Assertions.assertEquals(CURSO_NAO_ENCONTRADO,objectNotFoundException.getMessage());


    }
    @Test
    void cadastrarCurso() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(pessoaOptional);

        cursoService.cadastrarCurso(this.cursoForms);
        Mockito.verify(cursoRepository,Mockito.times(1)).save(Mockito.any());
    }
    @Test
    void cadastrarCursoExeptionObjectNotFound() {
        Mockito.when(pessoaService.buscarPessoa(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cursoService.cadastrarCurso(this.cursoForms));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }


    @Test
    void alterarCurso() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(pessoaOptional);
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenReturn(cursoOptional);

        Curso cursoAlterado = cursoService.buscarCurso(ID);

        cursoService.alterarCurso(ID,this.cursoForms);
        Mockito.verify(cursoRepository,Mockito.times(1)).save(Mockito.any());


        Assertions.assertEquals(cursoForms.getDescricao(), cursoAlterado.getDescricao());

    }
    @Test
    void alterarCursoExeptionObjectNotFoundCurso() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(pessoaOptional);
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(CURSO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cursoService.alterarCurso(ID,this.cursoForms));
        Assertions.assertEquals(CURSO_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }
    @Test
    void alterarCursoExeptionObjectNotFoundPessoa() {
        Mockito.when(pessoaService.buscarPessoa(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenReturn(cursoOptional);

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cursoService.alterarCurso(ID,this.cursoForms));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }


    @Test
    void deletarCurso() {
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenReturn(cursoOptional);
        Mockito.doNothing().when(cursoRepository).deleteById(Mockito.anyLong());

        cursoService.deletarCurso(ID);
        Mockito.verify(cursoRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    void deletarCursoExeptionObjectNotFound() {
        Mockito.when(cursoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(CURSO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cursoService.deletarCurso(ID));
        Assertions.assertEquals(CURSO_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }

    private void startCurso(){
        this.pessoa = new Pessoa();
        pessoa.setId(ID_PESSOA);
        pessoa.setUuid(UUID_PESSOA);
        this.pessoaDtoParaRelacionamento = new PessoaDtoParaRelacionamento(pessoa);
        this.curso = new Curso(ID, DESCRICAO,pessoa,pessoa);
        this.cursoDto = new CursoDto(ID,DESCRICAO,pessoaDtoParaRelacionamento,pessoaDtoParaRelacionamento);
        this.cursoForms = new CursoForms("novo curso cobool",1L,"iu1iguyg2h");
        this.cursoOptional = Optional.of(new Curso(ID, DESCRICAO,pessoa,pessoa));
        this.pessoaOptional = Optional.of(new Pessoa());

    }
}