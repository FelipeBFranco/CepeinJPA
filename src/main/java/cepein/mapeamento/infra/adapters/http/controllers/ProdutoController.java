package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.app.usecases.produto.*;
import cepein.mapeamento.infra.adapters.http.dtos.ProdutoDto;
import cepein.mapeamento.infra.adapters.http.forms.ProdutoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.ProdutoViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoGateway produtoGateway;

    public ProdutoController(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDto>> listarProdutos(){
        EncontrarListaDeProdutoUseCase encontrarListaDeProdutoUseCase = new EncontrarListaDeProdutoUseCase(this.produtoGateway);
        List<ProdutoDto> produtoDtoList = encontrarListaDeProdutoUseCase.encontrarListaProduto()
                .stream()
                .map(ProdutoViewModel::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok( produtoDtoList);
    }

    @GetMapping("/procurar-por-id/{id}")
    public ResponseEntity<ProdutoDto> procurarProduto(@PathVariable Long id){

        EncontrarProdutoUseCase encontrarProdutoUseCase = new EncontrarProdutoUseCase(this.produtoGateway);
        ProdutoDto produtoDto = ProdutoViewModel.toDto(encontrarProdutoUseCase.encontrarProdutoPorId(id));

        return ResponseEntity.ok(produtoDto);
    }

    @PostMapping("/cadastrar-produto-pessoa")
    public ResponseEntity<HttpStatus> cadastrarProdutoComPessoa(@RequestBody @Valid ProdutoForms produtoForms){

        CadastrarProdutoUseCase cadastrarProdutoUseCase = new CadastrarProdutoUseCase(this.produtoGateway);
        cadastrarProdutoUseCase.criarProduto(produtoForms.converter());

        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar-produto")
    public ResponseEntity<HttpStatus> atualizarProduto(@RequestBody @Valid ProdutoForms produtoForms){

        EncontrarProdutoUseCase encontrarProdutoUseCase = new EncontrarProdutoUseCase(this.produtoGateway);
        AtualizarProdutoUseCase atualizarProdutoUseCase =new AtualizarProdutoUseCase(this.produtoGateway);

        atualizarProdutoUseCase.atualizarProduto(produtoForms.converter(encontrarProdutoUseCase.encontrarProdutoPorId(produtoForms.getId())));

        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){

        DeletarProdutoUseCase deletarProdutoUseCase = new DeletarProdutoUseCase(this.produtoGateway);

        deletarProdutoUseCase.deletarProduto(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
