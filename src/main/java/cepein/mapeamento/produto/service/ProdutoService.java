package cepein.mapeamento.produto.service;

import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import cepein.mapeamento.pessoa_produto.PessoaProduto;
import cepein.mapeamento.pessoa_produto.PessoaProdutoId;
import cepein.mapeamento.pessoa_produto.repository.PessoaProdutoRepository;
import cepein.mapeamento.produto.dto.ProdutoDto;
import cepein.mapeamento.produto.forms.ProdutoForms;
import cepein.mapeamento.produto.model.Produto;
import cepein.mapeamento.produto.repository.ProdutoRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final PessoaRepository pessoaRepository;
    private final PessoaProdutoRepository pessoaProdutoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, PessoaRepository pessoaRepository, PessoaProdutoRepository pessoaProdutoRepository){
        this.produtoRepository = produtoRepository;
        this.pessoaRepository = pessoaRepository;
        this.pessoaProdutoRepository = pessoaProdutoRepository;
    }

    public Produto buscarProduto(Long idProduto){
        return this.produtoRepository.findById(idProduto)
                .orElseThrow(()-> new ObjectNotFoundException("Produto não encontrado"));
    }

    public ResponseEntity<List<ProdutoDto>> listarProdutos(){
        List<Produto> produtoList = this.produtoRepository.findAll();
        List<ProdutoDto> produtoDtoList = ProdutoDto.converter(produtoList);

        return ResponseEntity.ok(produtoDtoList);
    }

    public ResponseEntity<ProdutoDto> procurarProduto(Long idProduto){
        Produto produto = this.buscarProduto(idProduto);
        ProdutoDto produtoDto = new ProdutoDto(produto.getId(),
                produto.getDescricao(),
                PessoaDtoParaRelacionamento.converter(produto.getPessoaListComJoinTable()),
                PessoaDtoParaRelacionamento.converterPessoaProduto(produto.getPessoaListComEmbeddable()));
        return ResponseEntity.ok(produtoDto);
    }

    @Transactional
    public ResponseEntity<HttpStatus> cadastrarProdutoComPessoa(ProdutoForms produtoForms) {
        Produto produtoNovo = produtoForms.converter(new Produto());
        List<Long> listaDeIdsDasPessoas = this.pessoaRepository.findAllById(produtoForms.getListaDeIdsDasPessoas())
                .stream().map(Pessoa::getId).collect(Collectors.toList());

        listaDeIdsDasPessoas.forEach(t -> {
            this.pessoaProdutoRepository.save(new PessoaProduto
                    (new PessoaProdutoId(t, new Produto().getId()), this.pessoaRepository.findById(t)
                            .orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada!")),produtoNovo));
        });
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<Void> deletarProduto(Long idProduto){
        this.produtoRepository.deleteById(idProduto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    public ResponseEntity<HttpStatus> atualizarProduto(ProdutoForms produtoForms, Long idProduto) {
        Produto produto = this.buscarProduto(idProduto);
        Produto produtoAlterado = produtoForms.converter(produto);
        this.produtoRepository.save(produtoAlterado);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
