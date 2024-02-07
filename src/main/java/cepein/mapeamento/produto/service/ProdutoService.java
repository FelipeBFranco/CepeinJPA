package cepein.mapeamento.produto.service;

import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.produto.dto.ProdutoDto;
import cepein.mapeamento.produto.model.Produto;
import cepein.mapeamento.produto.repository.ProdutoRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
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
        ProdutoDto produtoDto =new ProdutoDto(produto.getId(),
                produto.getDescricao(),
                PessoaDtoParaRelacionamento.converter(produto.getPessoaListComJoinTable()),
                PessoaDtoParaRelacionamento.converterPessoaProduto(produto.getPessoaListComEmbeddable()));
        return ResponseEntity.ok(produtoDto);
    }
    @Transactional
    public ResponseEntity<Void> deletarProduto(Long idProduto){
        this.produtoRepository.deleteById(idProduto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
