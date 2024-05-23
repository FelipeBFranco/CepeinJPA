package cepein.mapeamento;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoCommand;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.app.usecases.endereco.*;
import cepein.mapeamento.infra.adapters.http.forms.EnderecoForms;
import exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class EnderecoTest {
   public static final Long ID = 15L;
    public static final String UUID = "cku1vufhjv";
    public static final String RUA = "Rua dos Loucos";
    public static final String CEP = "19800-001";
    public static final String CIDADE = "Assis";
    public static final String ESTADO = "SP";
    public static final Long ID_PESSOA = 1L;
    public static final String UUID_PESSOA = "khvv2jkv";
    public static final String ENDERECO_NAO_ENCONTRADO = "Endereço não encontrado";
    public static final int INDEX = 0;

    @Mock
    private EnderecoGateway enderecoGateway;
    @InjectMocks
    private EncontrarEnderecoUseCase encontrarEnderecoUseCase;
    @InjectMocks
    private EncontrarListaEnderecoUseCase encontrarListaEnderecoUseCase;
    @InjectMocks
    private CadastrarEnderecoUseCase cadastrarEnderecoUseCase;
    @InjectMocks
    private AtualizarEnderecoUseCase atualizarEnderecoUseCase;
    @InjectMocks
    private DeletarEnderecoUseCase deletarEnderecoUseCase;

    private EnderecoQuery enderecoQuery;
    private EnderecoForms enderecoForms;





    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startEndereco();
    }
    private void startEndereco(){
        this.iniciarRegrasFixture();
        this.enderecoQuery = Fixture.from(EnderecoQuery.class).gimme("valido");
    }
    private void iniciarRegrasFixture(){
        Fixture.of(PessoaQuery.class).addTemplate("valido", new Rule(){{
            add("id", ID_PESSOA);
            add("uuid",UUID_PESSOA);
        }});
        Fixture.of(EnderecoQuery.class).addTemplate("valido", new Rule(){{
            add("id_endereco",ID);
            add("uuid", UUID);
            add("rua", RUA);
            add("cep", CEP);
            add("cidade", CIDADE);
            add("estado", ESTADO);
            add("pessoaQueryPorId",fixture(PessoaQuery.class,"valido"));
            add("pessoaQueryPorUuid",fixture(PessoaQuery.class,"valido"));
        }});
        Fixture.of(EnderecoForms.class).addTemplate("criar", new Rule(){{
            add("uuid", UUID);
            add("rua", RUA);
            add("cep", CEP);
            add("cidade", CIDADE);
            add("estado", ESTADO);
        }});
        Fixture.of(EnderecoForms.class).addTemplate("atualizar", new Rule(){{
            add("id",ID);
            add("uuid", UUID);
            add("rua", "Avenida Brasil");
            add("cep", CEP);
            add("cidade", "Frutal do Campo");
            add("estado", ESTADO);
        }});
    }

    @Test
    void encontrarEnderecoSucesso() {
        Mockito.when(enderecoGateway.buscar(Mockito.anyLong())).thenReturn(enderecoQuery);

        EnderecoQuery response = encontrarEnderecoUseCase.execute(ID);


        Assertions.assertEquals(EnderecoQuery.class, response.getClass());
        Assertions.assertEquals(ID, response.getId_endereco());
        Assertions.assertEquals(UUID, response.getUuid());
        Assertions.assertEquals(RUA, response.getRua());
        Assertions.assertEquals(CEP, response.getCep());
        Assertions.assertEquals(CIDADE, response.getCidade());
        Assertions.assertEquals(ESTADO, response.getEstado());
        Assertions.assertEquals(ID_PESSOA, response.getPessoaQueryPorId().getId());
        Assertions.assertEquals(UUID_PESSOA, response.getPessoaQueryPorUuid().getUuid());
    }
    @Test
    void listarEnderecos() {
        Mockito.when(enderecoGateway.encontrarTodosOsEnderecos()).thenReturn(List.of(enderecoQuery));
        List<EnderecoQuery> response = encontrarListaEnderecoUseCase.execute();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(EnderecoQuery.class, response.get(INDEX).getClass());

        Assertions.assertEquals(enderecoQuery.getId_endereco(), response.get(INDEX).getId_endereco());
        Assertions.assertEquals(enderecoQuery.getUuid(), response.get(INDEX).getUuid());
        Assertions.assertEquals(enderecoQuery.getRua(), response.get(INDEX).getRua());
        Assertions.assertEquals(enderecoQuery.getCep(), response.get(INDEX).getCep());
        Assertions.assertEquals(enderecoQuery.getCidade(), response.get(INDEX).getCidade());
        Assertions.assertEquals(enderecoQuery.getEstado(), response.get(INDEX).getEstado());
        Assertions.assertEquals(enderecoQuery.getPessoaQueryPorId().getId(), response.get(INDEX).getPessoaQueryPorId().getId());
        Assertions.assertEquals(enderecoQuery.getPessoaQueryPorUuid().getUuid(), response.get(INDEX).getPessoaQueryPorUuid().getUuid());

    }
    @Test
    void encontrarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoGateway.buscar(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> encontrarEnderecoUseCase.execute(ID));

        Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }


    @Test
    void cadastrarEndereco() {

        this.enderecoForms = Fixture.from(EnderecoForms.class).gimme("criar");
        cadastrarEnderecoUseCase.execute(enderecoForms);
        Mockito.verify(enderecoGateway,Mockito.times(1)).salvar(Mockito.any());

    }
    @Test
    void alterarEndereco() {

        this.enderecoForms = Fixture.from(EnderecoForms.class).gimme("atualizar");
        Mockito.when(enderecoGateway.buscar(Mockito.anyLong())).thenReturn(enderecoQuery);
        EnderecoQuery enderocoAlterar = encontrarEnderecoUseCase.execute(ID);
        EnderecoCommand enderecoAlterado = enderecoForms.converter(enderocoAlterar);

        atualizarEnderecoUseCase.execute(this.enderecoForms);

        Mockito.verify(enderecoGateway,Mockito.times(1)).salvar(Mockito.any());

        Assertions.assertEquals(enderecoForms.getUuid(), enderecoAlterado.getUuid());
        Assertions.assertEquals(enderecoForms.getRua(), enderecoAlterado.getRua());
        Assertions.assertEquals(enderecoForms.getCep(), enderecoAlterado.getCep());
        Assertions.assertEquals(enderecoForms.getCidade(), enderecoAlterado.getCidade());
        Assertions.assertEquals(enderecoForms.getEstado(), enderecoAlterado.getEstado());
    }
    @Test
    void alterarEnderecoExeptionObjectNotFound() {
        this.enderecoForms = Fixture.from(EnderecoForms.class).gimme("atualizar");
        Mockito.when(enderecoGateway.buscar(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> atualizarEnderecoUseCase.execute(this.enderecoForms));

        Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void deletarEndereco() {
        Mockito.doNothing().when(enderecoGateway).deletar(Mockito.anyLong());

        deletarEnderecoUseCase.execute(ID);

        Mockito.verify(enderecoGateway,Mockito.times(1)).deletar(Mockito.anyLong());
    }
    @Test
    void deletarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoGateway.buscar(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> deletarEnderecoUseCase.execute(ID));

        Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

}
