package cepein.mapeamento.service;

import cepein.mapeamento.infra.forms.EnderecoForms;
import cepein.mapeamento.infra.forms.PessoaForms;
import cepein.mapeamento.infra.repository.*;
import cepein.mapeamento.infra.service.PessoaService;
import cepein.mapeamento.model.*;
import cepein.mapeamento.infra.dto.PessoaDto;
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
//vai quebrar, pois adicionei o pedido list a pessoa
class PessoaServiceTest {
    public static final int INDEX = 0;
    public static final long ID = 1L;
    public static final String NOME = "giovanna";
    public static final String UUID = "fde09967-6f88g";
    public static final long ID_ENDERECO = 1L;
    public static final String UUID_ENDERECO = "ffbdeb66-62d9-11ee-8c99-0242ac120002";
    public static final String PESSOA_NAO_ENCONTRADO = "Pessoa não encontrada";
    @Mock
    private PessoaRepository pessoaRepository ;

    @Mock
    private ProdutoRepository produtoRepository;
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private PessoaProdutoRepository pessoaProdutoRepository;
    @Mock
    private PessoaPedidoRepository pessoaPedidoRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;
    private Optional<Pessoa> pessoaOptional;
    private PessoaForms pessoaForms;

    private PessoaDto pessoaDto;
    private Endereco endereco;
    private  EnderecoForms enderecoForms;
    private List<Curso> cursoList;

    private List<Produto> produtoList;
    private List<PessoaProduto> pessoaProdutoList;
    private List<Pedido> pedidoList;
    private List<PessoaPedido> pessoaPedidoList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startPessoa();
    }
    @Test
    void buscarPessoa() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(pessoaOptional);
        Pessoa response = pessoaService.buscarPessoa(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Pessoa.class, response.getClass());

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(UUID, response.getUuid());
        Assertions.assertEquals(NOME, response.getNome());

        Assertions.assertEquals(pessoa.getEnderecoPorId(), response.getEnderecoPorId());
        Assertions.assertEquals(ID_ENDERECO, response.getEnderecoPorId().getId_endereco());
        Assertions.assertEquals(pessoa.getEnderecoPorUuid(), response.getEnderecoPorUuid());
        Assertions.assertEquals(UUID_ENDERECO, response.getEnderecoPorUuid().getUuid());

        Assertions.assertEquals(pessoa.getCursoPorId().get(INDEX).getId(), response.getCursoPorId().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getCursoPorId().get(INDEX).getClass(), response.getCursoPorId().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getCursoPorUuid().get(INDEX).getId(), response.getCursoPorUuid().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getCursoPorUuid().get(INDEX).getClass(), response.getCursoPorUuid().get(INDEX).getClass());

        Assertions.assertEquals(pessoa.getProdutoListComJoinTable().get(INDEX).getId(), response.getProdutoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getProdutoListComJoinTable().get(INDEX).getClass(), response.getProdutoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getProdutoListComEmbeddable().get(INDEX).getPessoaProdutoId(), response.getProdutoListComEmbeddable().get(INDEX).getPessoaProdutoId());
        Assertions.assertEquals(pessoa.getProdutoListComEmbeddable().get(INDEX).getClass(), response.getProdutoListComEmbeddable().get(INDEX).getClass());

        Assertions.assertEquals(pessoa.getPedidoListComJoinTable().get(INDEX).getId(), response.getPedidoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getPedidoListComJoinTable().get(INDEX).getClass(), response.getPedidoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getPedidoListComEmbeddable().get(INDEX).getPessoaPedidoId(), response.getPedidoListComEmbeddable().get(INDEX).getPessoaPedidoId());
        Assertions.assertEquals(pessoa.getPedidoListComEmbeddable().get(INDEX).getClass(), response.getPedidoListComEmbeddable().get(INDEX).getClass());

    }
    @Test
    void buscarPessoaExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> pessoaService.buscarPessoa(ID));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void listarPessoas() {
        Mockito.when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<PessoaDto> response = pessoaService.listarPessoas();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(PessoaDto.class,response.get(INDEX).getClass());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(UUID, response.get(INDEX).getUuid());
        Assertions.assertEquals(NOME, response.get(INDEX).getNome());

        Assertions.assertEquals(pessoaDto.getEnderecoPorId().getClass(), response.get(INDEX).getEnderecoPorId().getClass());
        Assertions.assertEquals(ID_ENDERECO, response.get(INDEX).getEnderecoPorId().getIdEndereco());
        Assertions.assertEquals(pessoaDto.getEnderecoPorUuid().getClass(), response.get(INDEX).getEnderecoPorUuid().getClass());
        Assertions.assertEquals(UUID_ENDERECO, response.get(INDEX).getEnderecoPorUuid().getUuid());

        Assertions.assertEquals(pessoaDto.getCursoListPorId().get(INDEX).getId(), response.get(INDEX).getCursoListPorId().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getCursoListPorId().get(INDEX).getClass(), response.get(INDEX).getCursoListPorId().get(INDEX).getClass());
        Assertions.assertEquals(pessoaDto.getCursoListPorUuid().get(INDEX).getId(), response.get(INDEX).getCursoListPorUuid().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getCursoListPorUuid().get(INDEX).getClass(), response.get(INDEX).getCursoListPorUuid().get(INDEX).getClass());

        Assertions.assertEquals(pessoaDto.getProdutoListComJoinTable().get(INDEX).getId(), response.get(INDEX).getProdutoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getProdutoListComJoinTable().get(INDEX).getClass(), response.get(INDEX).getProdutoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaDto.getProdutoListComEmbeddable().get(INDEX).getId(), response.get(INDEX).getProdutoListComEmbeddable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getProdutoListComEmbeddable().get(INDEX).getClass(), response.get(INDEX).getProdutoListComEmbeddable().get(INDEX).getClass());

        Assertions.assertEquals(pessoaDto.getPedidoListComJoinTable().get(INDEX).getId(), response.get(INDEX).getPedidoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getPedidoListComJoinTable().get(INDEX).getClass(), response.get(INDEX).getPedidoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaDto.getPedidoListComEmbeddable().get(INDEX).getId(), response.get(INDEX).getPedidoListComEmbeddable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getPedidoListComEmbeddable().get(INDEX).getClass(), response.get(INDEX).getPedidoListComEmbeddable().get(INDEX).getClass());


    }

    @Test
    void procurarPessoa() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(pessoaOptional);

        PessoaDto response = pessoaService.procurarPessoa(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(PessoaDto.class,response.getClass());

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(UUID, response.getUuid());
        Assertions.assertEquals(NOME, response.getNome());

        Assertions.assertEquals(pessoaDto.getEnderecoPorId().getClass(), response.getEnderecoPorId().getClass());
        Assertions.assertEquals(ID_ENDERECO, response.getEnderecoPorId().getIdEndereco());
        Assertions.assertEquals(pessoaDto.getEnderecoPorUuid().getClass(), response.getEnderecoPorUuid().getClass());
        Assertions.assertEquals(UUID_ENDERECO, response.getEnderecoPorUuid().getUuid());

        Assertions.assertEquals(pessoaDto.getCursoListPorId().get(INDEX).getId(), response.getCursoListPorId().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getCursoListPorId().get(INDEX).getClass(), response.getCursoListPorId().get(INDEX).getClass());
        Assertions.assertEquals(pessoaDto.getCursoListPorUuid().get(INDEX).getId(), response.getCursoListPorUuid().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getCursoListPorUuid().get(INDEX).getClass(), response.getCursoListPorUuid().get(INDEX).getClass());

        Assertions.assertEquals(pessoaDto.getProdutoListComJoinTable().get(INDEX).getId(), response.getProdutoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getProdutoListComJoinTable().get(INDEX).getClass(), response.getProdutoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaDto.getProdutoListComEmbeddable().get(INDEX).getId(), response.getProdutoListComEmbeddable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getProdutoListComEmbeddable().get(INDEX).getClass(), response.getProdutoListComEmbeddable().get(INDEX).getClass());

        Assertions.assertEquals(pessoaDto.getPedidoListComJoinTable().get(INDEX).getId(), response.getPedidoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getPedidoListComJoinTable().get(INDEX).getClass(), response.getPedidoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaDto.getPedidoListComEmbeddable().get(INDEX).getId(), response.getPedidoListComEmbeddable().get(INDEX).getId());
        Assertions.assertEquals(pessoaDto.getPedidoListComEmbeddable().get(INDEX).getClass(), response.getPedidoListComEmbeddable().get(INDEX).getClass());

    }
    @Test
    void procurarPessoaExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> pessoaService.procurarPessoa(ID));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    @Test
    void deletarPessoa() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(pessoaOptional);

        pessoaService.deletarPessoa(ID);

        Mockito.verify(pessoaRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    void deletarPessoaExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> pessoaService.deletarPessoa(ID));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    @Test
    void cadastrarPessoaComProduto() {
        pessoaService.cadastrarPessoaComProduto(this.pessoaForms);
        Mockito.verify(pessoaRepository,Mockito.times(1)).save(Mockito.any());
        Mockito.verify(pessoaProdutoRepository,Mockito.times(1)).saveAll(Mockito.anyList());
        Mockito.verify(pessoaPedidoRepository,Mockito.times(1)).saveAll(Mockito.anyList());
    }

    @Test
    void atualizarPessoa() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(pessoaOptional);
        Pessoa pessoaAlterada = pessoaService.buscarPessoa(ID);

        pessoaService.atualizarPessoa(this.pessoaForms,ID);

        Mockito.verify(pessoaRepository,Mockito.times(1)).save(Mockito.any());
        Mockito.verify(pessoaProdutoRepository,Mockito.times(1)).saveAll(Mockito.anyList());

        Assertions.assertNotNull(pessoaAlterada);
        Assertions.assertEquals(Pessoa.class, pessoaAlterada.getClass());

        Assertions.assertEquals( ID, pessoaAlterada.getId());
        Assertions.assertEquals(pessoaForms.getUuid(), pessoaAlterada.getUuid());
        Assertions.assertEquals(pessoaForms.getNome(), pessoaAlterada.getNome());

        Assertions.assertEquals(endereco, pessoaAlterada.getEnderecoPorId());
        Assertions.assertEquals(endereco.getId_endereco(), pessoaAlterada.getEnderecoPorId().getId_endereco());
        Assertions.assertEquals(pessoa.getEnderecoPorUuid(), pessoaAlterada.getEnderecoPorUuid());
        Assertions.assertEquals(enderecoForms.getUuid(), pessoaAlterada.getEnderecoPorUuid().getUuid());

        Assertions.assertEquals(pessoa.getCursoPorId().get(INDEX).getId(), pessoaAlterada.getCursoPorId().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getCursoPorId().get(INDEX).getClass(), pessoaAlterada.getCursoPorId().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getCursoPorUuid().get(INDEX).getId(), pessoaAlterada.getCursoPorUuid().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getCursoPorUuid().get(INDEX).getClass(), pessoaAlterada.getCursoPorUuid().get(INDEX).getClass());

        Assertions.assertEquals(pessoa.getProdutoListComJoinTable().get(INDEX).getId(), pessoaAlterada.getProdutoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getProdutoListComJoinTable().get(INDEX).getClass(), pessoaAlterada.getProdutoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getProdutoListComEmbeddable().get(INDEX).getPessoaProdutoId(), pessoaAlterada.getProdutoListComEmbeddable().get(INDEX).getPessoaProdutoId());
        Assertions.assertEquals(pessoa.getProdutoListComEmbeddable().get(INDEX).getClass(), pessoaAlterada.getProdutoListComEmbeddable().get(INDEX).getClass());

        Assertions.assertEquals(pessoa.getPedidoListComJoinTable().get(INDEX).getId(), pessoaAlterada.getPedidoListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoa.getPedidoListComJoinTable().get(INDEX).getClass(), pessoaAlterada.getPedidoListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoa.getPedidoListComEmbeddable().get(INDEX).getPessoaPedidoId(), pessoaAlterada.getPedidoListComEmbeddable().get(INDEX).getPessoaPedidoId());
        Assertions.assertEquals(pessoa.getPedidoListComEmbeddable().get(INDEX).getClass(), pessoaAlterada.getPedidoListComEmbeddable().get(INDEX).getClass());



    }
    @Test
    void atualizarPessoaExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PESSOA_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> pessoaService.deletarPessoa(ID));
        Assertions.assertEquals(PESSOA_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    private void startPessoa(){
        this.enderecoForms = new EnderecoForms("ffbdeb66-62d9-11ee-8c99-0242ac120002", "Avenida Brasil", "19000-100", "Frutal do Campo", "SP");

        this.endereco = enderecoForms.converter(new Endereco());
        endereco.setId_endereco(1L);

        this.pessoa = new Pessoa();
        pessoa.setId(ID);
        pessoa.setUuid(UUID);
        pessoa.setNome(NOME);

        this.cursoList = List.of(new Curso(1L, "Curso de Angular",pessoa,pessoa)
                ,new Curso(2L, "Curso de Spring Boot",pessoa,pessoa));

        Produto produto1 = new Produto(1L,"mouse",List.of(pessoa),List.of(new PessoaProduto()));
        Produto produto2 = new Produto(2L,"teclado",List.of(pessoa),List.of(new PessoaProduto()));

        this.pessoaProdutoList = List.of(new PessoaProduto(new PessoaProdutoId(pessoa.getId(),produto1.getId()),pessoa,produto1)
                ,new PessoaProduto(new PessoaProdutoId(pessoa.getId(),produto2.getId()),pessoa,produto2));
        produto1.setPessoaListComEmbeddable(pessoaProdutoList);
        produto2.setPessoaListComEmbeddable(pessoaProdutoList);
        this.produtoList = List.of(produto1,produto2);

    //    Pedido pedido1 = new Pedido(1L,"pedido1","ukyg8yf",List.of(pessoa),List.of(new PessoaPedido(new PessoaPedidoId(pessoa.getUuid(), pedido.getUuid()), pessoa.getUuid(), pedido.getUuid())));
    //    Pedido pedido2 = new Pedido(2L,"pedido2","yfl1gud",List.of(pessoa),List.of(new PessoaPedido(new PessoaPedidoId(pessoa.getUuid(), pedido.getUuid()), pessoa.getUuid(), pedido.getUuid())));

       /* this.pessoaPedidoList = List.of(new PessoaPedido(new PessoaPedidoId(pessoa.getUuid(),pedido1.getUuid()),pessoa,pedido1)
                ,new PessoaPedido(new PessoaPedidoId(pessoa.getUuid(),pedido2.getUuid()),pessoa,pedido2));
        pedido1.setPessoaListComEmbeddable(pessoaPedidoList);
        pedido2.setPessoaListComEmbeddable(pessoaPedidoList);
        this.pedidoList = List.of(pedido1,pedido2);*/


        this.pessoa = new Pessoa(ID, NOME, UUID
                ,endereco,endereco
                , cursoList, cursoList
                ,produtoList,pessoaProdutoList
                ,pedidoList,pessoaPedidoList);
        this.pessoaDto = new PessoaDto(pessoa);
        this.pessoaOptional = Optional.of(pessoa);
        this.pessoaForms = new PessoaForms("gigi",UUID,List.of(1L,2L),List.of(1L,2L)
                ,enderecoForms);

    }
}