package cepein.mapeamento.produto.service;

import cepein.mapeamento.produto.dto.ProdutoDto;
import cepein.mapeamento.produto.model.Produto;
import cepein.mapeamento.produto.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public ResponseEntity<List<ProdutoDto>> listarProdutos(){
        List<Produto> produtoList = this.produtoRepository.findAll();
        List<ProdutoDto> produtoDtoList = ProdutoDto.converter(produtoList);

        return ResponseEntity.ok(produtoDtoList);
    }
}
