package cepein.mapeamento.produto.reosurce;

import cepein.mapeamento.produto.dto.ProdutoDto;
import cepein.mapeamento.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoResource(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDto>> listarProdutos(){
        return this.produtoService.listarProdutos();
    }
    @GetMapping("procurar-por-id/{idProduto}")
    public ResponseEntity<ProdutoDto> procurarProduto(@PathVariable Long idProduto){
        return this.produtoService.procurarProduto(idProduto);
    }
    @DeleteMapping("/deletar/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long idProduto){
        return this.produtoService.deletarProduto(idProduto);
    }
}
