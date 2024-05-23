package cepein.mapeamento;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.acore.domain.models.pedido.PedidoCommand;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.app.usecases.pedido.*;
import cepein.mapeamento.infra.adapters.http.forms.EnderecoForms;
import cepein.mapeamento.infra.adapters.http.forms.PedidoForms;
import exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class PedidoTest {
    public static final Long ID = 1L;
    public static final String UUID = "1";
    public static final String DESCRICAO = "Chocolate Wily Wonka";
    public static final Long ID_PESSOA = 1L;
    public static final String UUID_PESSOA = "khvv2jkv";

    public static final int INDEX = 0;
    public static final String Pedido_NAO_ENCONTRADO = "PedidoQuery não encontrado";
    @Mock
    private PedidoGateway PedidoGateway;
    @InjectMocks
    private EncontrarPedidoUseCase  encontrarPedidoUseCase ;
    @InjectMocks
    private EncontrarListaDePedidoUseCase  encontrarListaDePedidoUseCase ;
    @InjectMocks
    private CadastrarPedidoUseCase  cadastrarPedidoUseCase ;
    @InjectMocks
    private AtualizarPedidoUseCase  atualizarPedidoUseCase ;
    @InjectMocks
    private DeletarPedidoUseCase  deletarPedidoUseCase ;

    private PedidoQuery pedidoQuery;
    private PedidoForms pedidoForms;
    private List<PessoaQuery> pessoaQueryList;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startPedido();

    }
    private void startPedido(){

        this.iniciarRegrasFixture();

        this.pessoaQueryList = Fixture.from(PessoaQuery.class).gimme(2,"valido");
        this.pedidoQuery = Fixture.from(PedidoQuery.class).gimme("valido");
    }
    private void iniciarRegrasFixture(){
        Fixture.of(PessoaQuery.class).addTemplate("valido", new Rule(){{
            add("id", ID_PESSOA);
            add("uuid",UUID_PESSOA);
        }});
        Fixture.of(PedidoQuery.class).addTemplate("valido", new Rule(){{
            add("id",ID);
            add("uuid", UUID);
            add("descricao", DESCRICAO);
            add("pessoaQueryListComJoinTable",fixture(PessoaQuery.class,2,"valido"));
            add("pessoaQueryListComEmbeddable",fixture(PessoaQuery.class,2,"valido"));
        }});
        Fixture.of(PedidoForms.class).addTemplate("criar", new Rule(){{
            add("uuid", UUID);
            add("descricao", "pedido novo");
        }});
        Fixture.of(PedidoForms.class).addTemplate("atualizar", new Rule(){{
            add("id",ID);
            add("uuid", UUID);
            add("descricao", "pedido novo");
        }});
    }

    @Test
    void encontrarPedido() {
        Mockito.when(PedidoGateway.buscar(Mockito.anyLong())).thenReturn(pedidoQuery);
        PedidoQuery response = encontrarPedidoUseCase.execute(ID);

        Assertions.assertEquals(PedidoQuery.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescricao());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getClass(), response.getPessoaQueryListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getId(), response.getPessoaQueryListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getClass(), response.getPessoaQueryListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaQueryList.get(INDEX).getId(), response.getPessoaQueryListComEmbeddable().get(INDEX).getId());
    }
    @Test
    void buscarPedidoExeptionObjectNotFound() {
        Mockito.when(PedidoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(Pedido_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> encontrarPedidoUseCase.execute(ID));
        Assertions.assertEquals(Pedido_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void listarPedidos() {
        Mockito.when(PedidoGateway.encontrarTodosOsPedidos()).thenReturn(List.of(pedidoQuery));
        List<PedidoQuery> response = encontrarListaDePedidoUseCase.execute();


        Assertions.assertEquals(PedidoQuery.class, response.get(INDEX).getClass());
        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(DESCRICAO, response.get(INDEX).getDescricao());
        Assertions.assertEquals(pedidoQuery.getPessoaQueryListComJoinTable().get(INDEX).getClass(), response.get(INDEX).getPessoaQueryListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pedidoQuery.getPessoaQueryListComJoinTable().get(INDEX).getId(), response.get(INDEX).getPessoaQueryListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pedidoQuery.getPessoaQueryListComEmbeddable().get(INDEX).getClass(), response.get(INDEX).getPessoaQueryListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(pedidoQuery.getPessoaQueryListComEmbeddable().get(INDEX).getId(), response.get(INDEX).getPessoaQueryListComEmbeddable().get(INDEX).getId());

    }
    @Test
    void cadastrarPedidoComPessoa() {
        this.pedidoForms = Fixture.from(PedidoForms.class).gimme("criar");
        cadastrarPedidoUseCase.execute(pedidoForms);
        Mockito.verify(PedidoGateway,Mockito.times(1)).salvar(Mockito.any());
    }
    @Test
    void atualizarPedido() {
        this.pedidoForms = Fixture.from(PedidoForms.class).gimme("atualizar");
        Mockito.when(PedidoGateway.buscar(Mockito.anyLong())).thenReturn(pedidoQuery);

        PedidoQuery pedidoQueryAlterar = encontrarPedidoUseCase.execute(ID);
        PedidoCommand pedidoQueryAlterado = pedidoForms.converter(pedidoQueryAlterar);
        atualizarPedidoUseCase.execute(this.pedidoForms);

        Assertions.assertEquals(PedidoCommand.class, pedidoQueryAlterado.getClass());

        Assertions.assertEquals(pedidoForms.getDescricao(), pedidoQueryAlterado.getDescricao());
    }
    @Test
    void atualizarPedidoExeptionObjectNotFound() {
        this.pedidoForms = Fixture.from(PedidoForms.class).gimme("atualizar");
        Mockito.when(PedidoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(Pedido_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> atualizarPedidoUseCase.execute(this.pedidoForms));
        Assertions.assertEquals(Pedido_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }
    @Test
    void deletarPedido() {
        Mockito.doNothing().when(PedidoGateway).deletar(Mockito.anyLong());

        deletarPedidoUseCase.execute(ID);

        Mockito.verify(PedidoGateway,Mockito.times(1)).deletar(Mockito.anyLong());
    }
    @Test
    void deletarPedidoExeptionObjectNotFound() {
        Mockito.when(PedidoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(Pedido_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> deletarPedidoUseCase.execute(ID));
        Assertions.assertEquals(Pedido_NAO_ENCONTRADO,objectNotFoundException.getMessage());
    }
}
