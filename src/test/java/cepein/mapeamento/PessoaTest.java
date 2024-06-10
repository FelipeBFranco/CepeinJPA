package cepein.mapeamento;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.app.gateways.*;
import cepein.mapeamento.app.usecases.pessoa.*;
import cepein.mapeamento.infra.adapters.http.forms.PessoaForms;
import cepein.mapeamento.infra.adapters.http.forms.PessoaPedidoForms;
import cepein.mapeamento.infra.adapters.http.forms.PessoaProdutoForms;
import exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class PessoaTest {
    public static final int INDEX = 0;
    public static final long ID = 1L;
    public static final String NOME = "giovanna";
    public static final String UUID = "fde09967-6f88g";
    public static final String PESSOA_NAO_ENCONTRADO = "PessoaQuery não encontrada";
    @Mock
    private PessoaGatway pessoaGatway ;
    @Mock
    private ProdutoGateway produtoGateway;
    @Mock
    private PedidoGateway pedidoGateway;
    @Mock
    private PessoaProdutoGateway pessoaProdutoGateway;
    @Mock
    private PessoaPedidoGateway pessoaPedidoGateway;
    @InjectMocks
    private EncontrarPessoaUseCase encontrarPessoaUseCase ;
    @InjectMocks
    private EncontrarListaDePessoaUseCase encontrarListaDePessoaUseCase ;
    @InjectMocks
    private CadastrarPessoaUseCase cadastrarPessoaUseCase ;
    @InjectMocks
    private CadastrarPessoaProdutoUseCase cadastrarPessoaProdutoUseCase;
    @InjectMocks
    private CadastrarPessoaPedidoUseCase cadastrarPessoaPedidoUseCase;
    @InjectMocks
    private AtualizarPessoaUseCase atualizarPessoaUseCase ;
    @InjectMocks
    private DeletarPessoaUseCase deletarPessoaUseCase ;


    private PessoaQuery pessoa;
    private PessoaForms pessoaForms;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startPessoa();
    }

    private void startPessoa(){
        this.iniciarRegrasFixture();

        this.pessoa = Fixture.from(PessoaQuery.class).gimme("valido");
        this.pessoaForms = new PessoaForms(1L,"gigi",UUID, 1L,"lij");

    }
    private void iniciarRegrasFixture(){
        Fixture.of(EnderecoQuery.class).addTemplate("valido", new Rule(){{
            add("id_endereco",1L);
            add("uuid", "eneneneneenee");
            add("rua", "endereco");
        }});
        Fixture.of(CursoQuery.class).addTemplate("valido", new Rule(){{
            add("id",1L);
            add("descricao", "Curso");
        }});
        Fixture.of(ProdutoQuery.class).addTemplate("valido", new Rule(){{
            add("id",1L);
            add("descricao", "Produto");
        }});
        Fixture.of(PedidoQuery.class).addTemplate("valido", new Rule(){{
            add("id",1L);
            add("uuid", "pepepepepepe");
            add("descricao", "Pedido");
        }});
        Fixture.of(PessoaQuery.class).addTemplate("valido", new Rule(){{
            add("id", ID);
            add("nome",NOME);
            add("uuid",UUID);
            add("enderecoQueryPorId",fixture(EnderecoQuery.class,"valido"));
            add("enderecoQueryPorUuid",fixture(EnderecoQuery.class,"valido"));
            add("cursoPorId",fixture(CursoQuery.class,2,"valido"));
            add("cursoPorUuid",fixture(CursoQuery.class,2,"valido"));
            add("produtoQueryListComJoinTable",fixture(ProdutoQuery.class,2,"valido"));
            add("produtoQueryListComEmbeddable",fixture(ProdutoQuery.class,2,"valido"));
            add("pedidoQueryListComJoinTable",fixture(PedidoQuery.class,2,"valido"));
            add("pedidoQueryListComEmbeddable",fixture(PedidoQuery.class,2,"valido"));

        }});
    }
    @Test
    void buscarPessoa() {
        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenReturn(pessoa);
        PessoaQuery response = encontrarPessoaUseCase.execute(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(PessoaQuery.class, response.getClass());

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(UUID, response.getUuid());
        Assertions.assertEquals(NOME, response.getNome());

        Assertions.assertEquals(pessoa.getEnderecoQueryPorId(), response.getEnderecoQueryPorId());
        Assertions.assertEquals(pessoa.getEnderecoQueryPorId().getId_endereco(), response.getEnderecoQueryPorId().getId_endereco());
        Assertions.assertEquals(pessoa.getEnderecoQueryPorUuid(), response.getEnderecoQueryPorUuid());
        Assertions.assertEquals(pessoa.getEnderecoQueryPorId().getUuid(), response.getEnderecoQueryPorUuid().getUuid());

        Assertions.assertEquals(pessoa.getCursoPorId().get(INDEX).getId(), response.getCursoPorId().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getCursoPorId().get(INDEX).getClass(), response.getCursoPorId().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getCursoPorUuid().get(INDEX).getId(), response.getCursoPorUuid().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getCursoPorUuid().get(INDEX).getClass(), response.getCursoPorUuid().get(INDEX).getClass());

        Assertions.assertEquals(pessoa.getPedidoQueryListComJoinTable().get(INDEX).getId(), response.getProdutoQueryListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getProdutoQueryListComJoinTable().get(INDEX).getClass(), response.getProdutoQueryListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getProdutoQueryListComEmbeddable().get(INDEX).getId(), response.getProdutoQueryListComEmbeddable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getProdutoQueryListComEmbeddable().get(INDEX).getClass(), response.getProdutoQueryListComEmbeddable().get(INDEX).getClass());

        Assertions.assertEquals(pessoa.getPedidoQueryListComJoinTable().get(INDEX).getId(), response.getPedidoQueryListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getPedidoQueryListComJoinTable().get(INDEX).getClass(), response.getPedidoQueryListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getPedidoQueryListComEmbeddable().get(INDEX).getId(), response.getPedidoQueryListComEmbeddable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getPedidoQueryListComEmbeddable().get(INDEX).getClass(), response.getPedidoQueryListComEmbeddable().get(INDEX).getClass());

    }
    @Test
    void listarPessoas() {
        Mockito.when(pessoaGatway.encontrarTodasAsPessoa()).thenReturn(List.of(pessoa));

        List<PessoaQuery> response = encontrarListaDePessoaUseCase.execute();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(PessoaQuery.class,response.get(INDEX).getClass());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(UUID, response.get(INDEX).getUuid());
        Assertions.assertEquals(NOME, response.get(INDEX).getNome());
    }
    @Test
    void buscarPessoaExeptionObjectNotFound() {
        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> encontrarPessoaUseCase.execute(ID));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    @Test
    void cadastrarPessoa() {
        cadastrarPessoaUseCase.execute(this.pessoaForms);
        Mockito.verify(pessoaGatway,Mockito.times(1)).salvar(Mockito.any());
    }
    @Test
    void cadastrarPessoaProduto() {
        ProdutoQuery produtoQuery = Fixture.from(ProdutoQuery.class).gimme("valido");

        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenReturn(pessoa);
        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenReturn(produtoQuery);

        PessoaProdutoForms pessoaProdutoForms = new PessoaProdutoForms(ID,List.of(1L,2L));

        cadastrarPessoaProdutoUseCase.execute(pessoaProdutoForms);
        Mockito.verify(pessoaProdutoGateway,Mockito.times(1)).salvar(Mockito.any());
    }
    @Test
    void cadastrarPessoaProdutoExeptionObjectNotFoundPessoa() {
        ProdutoQuery produtoQuery = Fixture.from(ProdutoQuery.class).gimme("valido");
        PessoaProdutoForms pessoaProdutoForms = new PessoaProdutoForms(ID,List.of(1L,2L));

        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenReturn(produtoQuery);
        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cadastrarPessoaProdutoUseCase.execute(pessoaProdutoForms));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void cadastrarPessoaProdutoExeptionObjectNotFoundProduto() {
        PessoaProdutoForms pessoaProdutoForms = new PessoaProdutoForms(ID,List.of(1L,2L));

        Mockito.when(produtoGateway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException("Produto não encontrado"));

        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenReturn(pessoa);
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cadastrarPessoaProdutoUseCase.execute(pessoaProdutoForms));
        Assertions.assertEquals("Produto não encontrado",objectNotFoundException.getMessage());

    }
    @Test
    void cadastrarPedidoProduto() {
        PedidoQuery pedidoQuery = Fixture.from(PedidoQuery.class).gimme("valido");

        Mockito.when(pessoaGatway.buscar(Mockito.anyString())).thenReturn(pessoa);
        Mockito.when(pedidoGateway.buscar(Mockito.anyString())).thenReturn(pedidoQuery);

        PessoaPedidoForms pessoaPedidoForms = new PessoaPedidoForms(UUID,List.of("1","2"));

        cadastrarPessoaPedidoUseCase.execute(pessoaPedidoForms);
        Mockito.verify(pessoaPedidoGateway,Mockito.times(1)).salvar(Mockito.any());
    }
    @Test
    void cadastrarPessoaPedidoExeptionObjectNotFoundPessoa() {
        PedidoQuery pedidoQuery = Fixture.from(PedidoQuery.class).gimme("valido");
        PessoaPedidoForms pessoaPedidoForms = new PessoaPedidoForms(UUID,List.of("1","2"));

        Mockito.when(pedidoGateway.buscar(Mockito.anyString())).thenReturn(pedidoQuery);
        Mockito.when(pessoaGatway.buscar(Mockito.anyString())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cadastrarPessoaPedidoUseCase.execute(pessoaPedidoForms));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void cadastrarPessoaPedidoExeptionObjectNotFoundProduto() {
        PessoaPedidoForms pessoaPedidoForms = new PessoaPedidoForms(UUID,List.of("1","2"));

        Mockito.when(pedidoGateway.buscar(Mockito.anyString())).thenThrow(new ObjectNotFoundException("Pedido não encontrado"));

        Mockito.when(pessoaGatway.buscar(Mockito.anyString())).thenReturn(pessoa);
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> cadastrarPessoaPedidoUseCase.execute(pessoaPedidoForms));
        Assertions.assertEquals("Pedido não encontrado",objectNotFoundException.getMessage());

    }
    @Test
    void atualizarPessoa() {
        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenReturn(pessoa);

        atualizarPessoaUseCase.execute(this.pessoaForms);

        Mockito.verify(pessoaGatway,Mockito.times(1)).salvar(Mockito.any());

    }
    @Test
    void atualizarPessoaExeptionObjectNotFound() {
        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> atualizarPessoaUseCase.execute(this.pessoaForms));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void deletarPessoa() {
        Mockito.doNothing().when(pessoaGatway).deletar(Mockito.anyLong());
        deletarPessoaUseCase.execute(ID);

        Mockito.verify(pessoaGatway,Mockito.times(1)).deletar(Mockito.anyLong());
    }
    @Test
    void deletarPessoaExeptionObjectNotFound() {
        Mockito.when(pessoaGatway.buscar(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> deletarPessoaUseCase.execute(ID));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
}
