package cepein.mapeamento.service;

import cepein.mapeamento.infra.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.infra.service.ProdutoService;
import cepein.mapeamento.model.Pessoa;
import cepein.mapeamento.model.PessoaProdutoId;
import cepein.mapeamento.infra.repository.PessoaProdutoRepository;
import cepein.mapeamento.infra.repository.PessoaRepository;
import cepein.mapeamento.model.PessoaProduto;
import cepein.mapeamento.infra.dto.ProdutoDto;
import cepein.mapeamento.infra.forms.ProdutoForms;
import cepein.mapeamento.model.Produto;
import cepein.mapeamento.infra.repository.ProdutoRepository;
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

class ProdutoServiceTest {
    public static final Long ID = 1L;
    public static final String DESCRICAO = "Chocolate Wily Wonka";

    public static final int INDEX = 0;
    public static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private PessoaProdutoRepository pessoaProdutoRepository;

    @InjectMocks
    private ProdutoService produtoService;


    private Produto produto;
    private ProdutoDto produtoDto;
    private ProdutoForms produtoForms;
    private Optional<Produto> produtoOptional;

    private List<Pessoa> pessoaList;
    List<PessoaProduto> pessoaProdutoList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startProduto();

    }

    @Test
    void buscarProduto() {
        Mockito.when(produtoRepository.findById(Mockito.anyLong())).thenReturn(produtoOptional);
        Produto response = produtoService.buscarProduto(ID);

        Assertions.assertEquals(Produto.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescricao());
        Assertions.assertEquals(pessoaList.get(INDEX).getClass(), response.getPessoaListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaList.get(INDEX).getId(), response.getPessoaListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaProdutoList.get(INDEX).getClass(), response.getPessoaListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaProdutoList.get(INDEX).getPessoaProdutoId(), response.getPessoaListComEmbeddable().get(INDEX).getPessoaProdutoId());
    }
    @Test
    void buscarProdutoExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> produtoService.buscarProduto(ID));
        Assertions.assertEquals(PRODUTO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void listarProdutos() {
        Mockito.when(produtoRepository.findAll()).thenReturn(List.of(produto));
        List<ProdutoDto> response = this.produtoService.listarProdutos();


        Assertions.assertEquals(ProdutoDto.class, response.get(INDEX).getClass());
        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(DESCRICAO, response.get(INDEX).getDescricao());
        Assertions.assertEquals(produtoDto.getPessoaListComJoinTable().get(INDEX).getClass(), response.get(INDEX).getPessoaListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(produtoDto.getPessoaListComJoinTable().get(INDEX).getId(), response.get(INDEX).getPessoaListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(produtoDto.getPessoaListComEmbeddable().get(INDEX).getClass(), response.get(INDEX).getPessoaListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(produtoDto.getPessoaListComEmbeddable().get(INDEX).getId(), response.get(INDEX).getPessoaListComEmbeddable().get(INDEX).getId());

    }

    @Test
    void procurarProduto() {
        Mockito.when(produtoRepository.findById(Mockito.anyLong())).thenReturn(produtoOptional);
        ProdutoDto response = produtoService.procurarProduto(ID);

        Assertions.assertEquals(ProdutoDto.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(DESCRICAO, response.getDescricao());
        Assertions.assertEquals(produtoDto.getPessoaListComJoinTable().get(INDEX).getClass(), response.getPessoaListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(produtoDto.getPessoaListComJoinTable().get(INDEX).getId(), response.getPessoaListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(produtoDto.getPessoaListComEmbeddable().get(INDEX).getClass(), response.getPessoaListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(produtoDto.getPessoaListComEmbeddable().get(INDEX).getId(), response.getPessoaListComEmbeddable().get(INDEX).getId());


    }
    @Test
    void procurarProdutoExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> produtoService.procurarProduto(ID));
        Assertions.assertEquals(PRODUTO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    @Test
    void cadastrarProdutoComPessoa() {
        produtoService.cadastrarProdutoComPessoa(this.produtoForms);
        Mockito.verify(produtoRepository,Mockito.times(1)).save(Mockito.any());
        Mockito.verify(pessoaProdutoRepository,Mockito.times(1)).saveAll(Mockito.anyList());
    }

    @Test
    void deletarProduto() {
        Mockito.when(produtoRepository.findById(Mockito.anyLong())).thenReturn(produtoOptional);
        produtoService.deletarProduto(ID);
        Mockito.verify(produtoRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    void deletarProdutoExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> produtoService.deletarProduto(ID));
        Assertions.assertEquals(PRODUTO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    @Test
    void atualizarProduto() {
        Mockito.when(produtoRepository.findById(Mockito.anyLong())).thenReturn(produtoOptional);

        Produto produtoAlterado = produtoService.buscarProduto(ID);
        produtoService.atualizarProduto(this.produtoForms,ID);

        Assertions.assertEquals(Produto.class, produtoAlterado.getClass());
        Assertions.assertEquals(ID, produtoAlterado.getId());
        Assertions.assertEquals(produtoForms.getDescricao(), produtoAlterado.getDescricao());
        Assertions.assertEquals(pessoaList.get(INDEX).getClass(), produtoAlterado.getPessoaListComJoinTable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaList.get(INDEX).getId(), produtoAlterado.getPessoaListComJoinTable().get(INDEX).getId());
        Assertions.assertEquals(pessoaProdutoList.get(INDEX).getClass(), produtoAlterado.getPessoaListComEmbeddable().get(INDEX).getClass());
        Assertions.assertEquals(pessoaProdutoList.get(INDEX).getPessoaProdutoId(), produtoAlterado.getPessoaListComEmbeddable().get(INDEX).getPessoaProdutoId());



    }
    @Test
    void atualizarProdutoExeptionObjectNotFound() {
        Mockito.when(pessoaRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(PRODUTO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> produtoService.atualizarProduto(this.produtoForms,ID));
        Assertions.assertEquals(PRODUTO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    private void startProduto(){
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        this.pessoaList = List.of(pessoa1,pessoa2);

        this.produto = new Produto();
        this.produto.setId(ID);
        this.produto.setDescricao(DESCRICAO);
        this.produto.setPessoaListComJoinTable(pessoaList);

        this.pessoaProdutoList = List.of(new PessoaProduto(new PessoaProdutoId(pessoa1.getId(),produto.getId()),pessoa1,produto)
                ,new PessoaProduto(new PessoaProdutoId(pessoa2.getId(),produto.getId()),pessoa2,produto));

        pessoa1.setProdutoListComEmbeddable(pessoaProdutoList);
        pessoa2.setProdutoListComEmbeddable(pessoaProdutoList);

        this.produto.setPessoaListComEmbeddable(pessoaProdutoList);

        this.produtoDto = new ProdutoDto(ID,DESCRICAO,PessoaDtoParaRelacionamento.converter(pessoaList)
                ,PessoaDtoParaRelacionamento.converter(pessoaList));
        this.produtoForms = new ProdutoForms("troca",List.of(1L,2L));
        this.produtoOptional = Optional.of(new Produto(ID, DESCRICAO,pessoaList ,pessoaProdutoList ));


    }
}