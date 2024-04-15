package cepein.mapeamento.infra.resource;

import cepein.mapeamento.infra.dto.ProdutoDto;
import cepein.mapeamento.infra.forms.ProdutoForms;
import cepein.mapeamento.infra.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok( this.produtoService.listarProdutos());
    }

    @GetMapping("/procurar-por-id/{idProduto}")
    public ResponseEntity<ProdutoDto> procurarProduto(@PathVariable Long idProduto){
        return ResponseEntity.ok(this.produtoService.procurarProduto(idProduto));
    }

    @PostMapping("/cadastrar-produto-pessoa")
    public ResponseEntity<HttpStatus> cadastrarProdutoComPessoa(@RequestBody @Valid ProdutoForms produtoForms){
        this.produtoService.cadastrarProdutoComPessoa(produtoForms);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar-produto/{idProduto}")
    public ResponseEntity<HttpStatus> atualizarProduto(@RequestBody @Valid ProdutoForms produtoForms, @PathVariable Long idProduto){
        this.produtoService.atualizarProduto(produtoForms, idProduto);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long idProduto){
        this.produtoService.deletarProduto(idProduto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
