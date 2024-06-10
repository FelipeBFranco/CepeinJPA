package cepein.mapeamento;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.models.curso.CursoCommand;
import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.app.usecases.curso.*;
import cepein.mapeamento.infra.adapters.http.forms.CursoForms;
import exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class CursoTest {
    public static final Long ID = 1L;
    public static final String DESCRICAO = "Cobol do Vitão";
    public static final Long ID_PESSOA = 1L;
    public static final String UUID_PESSOA = "lhvhv2vu";
    public static final int INDEX = 0;
    public static final String CURSO_NAO_ENCONTRADO = "Curso não encontrado";

    @Mock
    private CursoGateway cursoGateway;
    @InjectMocks
    private EncontrarCursoUseCase encontrarCursoUseCase;
    @InjectMocks
    private EncontrarListaCursoUseCase encontrarListaCursoUseCase;
    @InjectMocks
    private CadastrarCursoUseCase cadastrarCursoUseCase;
    @InjectMocks
    private AtualizarCursoUseCase atualizarCursoUseCase;
    @InjectMocks
    private DeletarCursoUseCase deletarCursoUseCase;

    private CursoQuery curso;
    private CursoForms cursoForms;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startCurso();
    }
    private void startCurso() {
        this.iniciarRegrasFixture();
        this.curso = Fixture.from(CursoQuery.class).gimme("valido");
    }
    private void iniciarRegrasFixture(){
        Fixture.of(PessoaQuery.class).addTemplate("valido", new Rule(){{
            add("id", ID_PESSOA);
            add("uuid",UUID_PESSOA);
        }});
        Fixture.of(CursoQuery.class).addTemplate("valido", new Rule(){{
            add("id",ID);
            add("descricao", DESCRICAO);
            add("pessoaQueryPorId",fixture(PessoaQuery.class,"valido"));
            add("pessoaQueryPorUuid",fixture(PessoaQuery.class,"valido"));
        }});
        Fixture.of(CursoForms.class).addTemplate("valido", new Rule(){{
            add("descricao", "novo curso cobool");
            add("idPessoa",ID_PESSOA);
            add("uuidPessoa", UUID_PESSOA);
        }});
    }

    @Test
    @DisplayName("Devolve um CursoQuery, se o id existir no banco")
    void buscarCurso(){
        Mockito.when(cursoGateway.buscar(Mockito.anyLong())).thenReturn(curso);
        CursoQuery response = encontrarCursoUseCase.execute(ID);

        Assertions.assertEquals(CursoQuery.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescricao());
        Assertions.assertEquals(ID_PESSOA, response.getPessoaQueryPorId().getId());
        Assertions.assertEquals(UUID_PESSOA, response.getPessoaQueryPorUuid().getUuid());
    }
    @Test
    @DisplayName("Devolve uma Exception ao buscar, se o id não existir no banco")
    void buscarCursoExeptionObjectNotFound() {
        Mockito.when(cursoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(CURSO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> encontrarCursoUseCase.execute(ID));
        Assertions.assertEquals(CURSO_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }
    @Test
    @DisplayName("Devolve uma lista de CursoQuery")
    void listarCursos() {
        Mockito.when(cursoGateway.encontrarTodosOsCursos()).thenReturn(List.of(curso));
        List<CursoQuery> response = encontrarListaCursoUseCase.execute();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(CursoQuery.class, response.get(INDEX).getClass());

        Assertions.assertEquals(curso.getId(), response.get(INDEX).getId());
        Assertions.assertEquals(curso.getDescricao(), response.get(INDEX).getDescricao());
        Assertions.assertEquals(curso.getPessoaQueryPorId().getId(), response.get(INDEX).getPessoaQueryPorId().getId());
        Assertions.assertEquals(curso.getPessoaQueryPorUuid().getUuid(), response.get(INDEX).getPessoaQueryPorUuid().getUuid());
    }

    @Test
    @DisplayName("Cadastra um novo curso no banco se estiver tudo ok")
    void cadastrarCurso() {
        this.cursoForms = Fixture.from(CursoForms.class).gimme("valido");
        cadastrarCursoUseCase.execute(this.cursoForms);
        Mockito.verify(cursoGateway,Mockito.times(1)).salvar(Mockito.any());
    }
    @Test
    @DisplayName("Atualiza um curso existente no banco se estiver tudo ok")
    void alterarCurso() {
        this.cursoForms = Fixture.from(CursoForms.class).gimme("valido",new Rule(){{
            add("id",ID);
        }});
        Mockito.when(cursoGateway.buscar(Mockito.anyLong())).thenReturn(this.curso);

        CursoQuery cursoAlterar = encontrarCursoUseCase.execute(ID);
        CursoCommand cursoAlterado = cursoForms.converter(cursoAlterar);

        atualizarCursoUseCase.execute(this.cursoForms);

        Mockito.verify(cursoGateway,Mockito.times(1)).salvar(Mockito.any());

        Assertions.assertEquals(cursoForms.getDescricao(), cursoAlterado.getDescricao());
    }
    @Test
    @DisplayName("Devolve uma Exception ao atualizar, se o id do curso não existir no banco")
    void alterarCursoExeptionObjectNotFound() {
        this.cursoForms = Fixture.from(CursoForms.class).gimme("valido",new Rule(){{
            add("id",ID);
        }});
        Mockito.when(cursoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(CURSO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> atualizarCursoUseCase.execute(this.cursoForms));
        Assertions.assertEquals(CURSO_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }
    @Test
    @DisplayName("Deleta o curso se existir no banco")
    void deletarCurso() {
        Mockito.doNothing().when(cursoGateway).deletar(Mockito.anyLong());

        deletarCursoUseCase.execute(ID);
        Mockito.verify(cursoGateway,Mockito.times(1)).deletar(Mockito.anyLong());
    }
    @Test
    @DisplayName("Devolve uma Exception ao deletar, se o id do curso não existir no banco")
    void deletarCursoExeptionObjectNotFound() {
        this.cursoForms = Fixture.from(CursoForms.class).gimme("valido",new Rule(){{
            add("id",ID);
        }});
        Mockito.when(cursoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(CURSO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> deletarCursoUseCase.execute(ID));
        Assertions.assertEquals(CURSO_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }
}
