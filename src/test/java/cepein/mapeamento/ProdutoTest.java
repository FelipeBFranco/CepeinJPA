package cepein.mapeamento;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.models.produto.ProdutoCommand;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.app.usecases.produto.*;
import cepein.mapeamento.infra.adapters.http.forms.ProdutoForms;
import exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ProdutoTest {
    private static final Long ID = 1L;
    private static final String DESCRICAO = "Chocolate Wily Wonka";
    private static final Long ID_PESSOA = 1L;
    private static final String UUID_PESSOA = "khvv2jkv";

    private static final int INDEX = 0;
    private static final String PRODUTO_NAO_ENCONTRADO = "ProdutoQuery não encontrado";
    @Mock
    private ProdutoGateway produtoGateway;
    @InjectMocks
    private EncontrarProdutoUseCase encontrarProdutoUseCase ;
    @InjectMocks
    private EncontrarListaDeProdutoUseCase encontrarListaDeProdutoUseCase ;
    @InjectMocks
    private CadastrarProdutoUseCase cadastrarProdutoUseCase ;
    @InjectMocks
    private AtualizarProdutoUseCase  atualizarProdutoUseCase ;
    @InjectMocks
    private DeletarProdutoUseCase  deletarProdutoUseCase ;

    private ProdutoQuery produtoQuery;

    private ProdutoForms produtoForms;
    private List<PessoaQuery> pessoaQueryList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startProduto();

    }
    private void startProduto(){
        this.iniciarRegrasFixture();

        this.pessoaQueryList = Fixture.from(PessoaQuery.class).gimme(2,"valido");

        this.produtoQuery = Fixture.from(ProdutoQuery.class).gimme("valido");
    }
    private void iniciarRegrasFixture(){
        Fixture.of(PessoaQuery.class).addTemplate("valido", new Rule(){{
            add("id", ID_PESSOA);
            add("uuid",UUID_PESSOA);
        }});
        Fixture.of(ProdutoQuery.class).addTemplate("valido", new Rule(){{
            add("id",ID);
            add("descricao", DESCRICAO);
            add("pessoaQueryListComJoinTable",fixture(PessoaQuery.class,2,"valido"));
            add("pessoaQueryListComEmbeddable",fixture(PessoaQuery.class,2,"valido"));
        }});
        Fixture.of(ProdutoForms.class).addTemplate("criar", new Rule(){{
            add("descricao", "produto novo");
        }});
        Fixture.of(ProdutoForms.class).addTemplate("atualizar", new Rule(){{
            add("id",ID);
            add("descricao", "produto novo");
        }});
    }
    @Test
    void encontrarProduto() {
        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenReturn(produtoQuery);
        ProdutoQuery response = encontrarProdutoUseCase.execute(ID);

        Assertions.assertEquals(ProdutoQuery.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescricao());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getClass(), response.getPessoaQueryListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getId(), response.getPessoaQueryListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getClass(), response.getPessoaQueryListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getId(), response.getPessoaQueryListComEmbeddable().get(INDEX).getId());
    }
    @Test
    void buscarProdutoExeptionObjectNotFound() {
        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> encontrarProdutoUseCase.execute(ID));

        Assertions.assertEquals(PRODUTO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void listarProdutos() {
        Mockito.when(produtoGateway.encontrarTodasOsProduto()).thenReturn(List.of(produtoQuery));
        List<ProdutoQuery> response = encontrarListaDeProdutoUseCase.execute();

        Assertions.assertEquals(ProdutoQuery.class, response.get(INDEX).getClass());
        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(DESCRICAO, response.get(INDEX).getDescricao());
        Assertions.assertEquals(produtoQuery.getPessoaQueryListComJoinTable().get(INDEX).getClass(), response.get(INDEX).getPessoaQueryListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(produtoQuery.getPessoaQueryListComJoinTable().get(INDEX).getId(), response.get(INDEX).getPessoaQueryListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(produtoQuery.getPessoaQueryListComEmbeddable().get(INDEX).getClass(), response.get(INDEX).getPessoaQueryListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(produtoQuery.getPessoaQueryListComEmbeddable().get(INDEX).getId(), response.get(INDEX).getPessoaQueryListComEmbeddable().get(INDEX).getId());

    }
    @Test
    void cadastrarProdutoComPessoa() {
        this.produtoForms = Fixture.from(ProdutoForms.class).gimme("criar");
        cadastrarProdutoUseCase.execute(produtoForms);
        Mockito.verify(produtoGateway,Mockito.times(1)).salvar(Mockito.any());
    }
    @Test
    void atualizarProduto() {
        this.produtoForms = Fixture.from(ProdutoForms.class).gimme("atualizar");
        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenReturn(produtoQuery);

        ProdutoQuery produtoQueryAlterar = encontrarProdutoUseCase.execute(ID);
        ProdutoCommand produtoQueryAlterado = produtoForms.converter(produtoQueryAlterar);
        atualizarProdutoUseCase.execute(this.produtoForms);

        Assertions.assertEquals(ProdutoCommand.class, produtoQueryAlterado.getClass());
        Assertions.assertEquals(produtoForms.getDescricao(), produtoQueryAlterado.getDescricao());
    }
    @Test
    void atualizarProdutoExeptionObjectNotFound() {

        this.produtoForms = Fixture.from(ProdutoForms.class).gimme("atualizar");
        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> atualizarProdutoUseCase.execute(this.produtoForms));

        Assertions.assertEquals(PRODUTO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void deletarProduto() {
        Mockito.doNothing().when(produtoGateway).deletar(Mockito.anyLong());

        deletarProdutoUseCase.execute(ID);

        Mockito.verify(produtoGateway,Mockito.times(1)).deletar(Mockito.anyLong());
    }
    @Test
    void deletarProdutoExeptionObjectNotFound() {
        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NAO_ENCONTRADO));

        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> deletarProdutoUseCase.execute(ID));

        Assertions.assertEquals(PRODUTO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
}
