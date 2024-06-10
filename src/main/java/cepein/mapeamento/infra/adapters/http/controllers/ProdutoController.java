package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.app.usecases.produto.*;
import cepein.mapeamento.infra.adapters.http.dtos.ProdutoDto;
import cepein.mapeamento.infra.adapters.http.forms.ProdutoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.ProdutoViewModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoGateway produtoGateway;
    @Autowired
    public ProdutoController(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProdutoDto> procurarProduto(@PathVariable Long id){

        EncontrarProdutoUseCase encontrarProdutoUseCase = new EncontrarProdutoUseCase(this.produtoGateway);
        ProdutoDto produtoDto = ProdutoViewModel.toDto(encontrarProdutoUseCase.execute(id));

        return ResponseEntity.ok(produtoDto);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDto>> listarProdutos(){
        EncontrarListaDeProdutoUseCase encontrarListaDeProdutoUseCase = new EncontrarListaDeProdutoUseCase(this.produtoGateway);
        List<ProdutoDto> produtoDtoList = encontrarListaDeProdutoUseCase.execute()
                .stream()
                .map(ProdutoViewModel::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok( produtoDtoList);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarProdutoComPessoa(@RequestBody @Valid ProdutoForms produtoForms){

        CadastrarProdutoUseCase cadastrarProdutoUseCase = new CadastrarProdutoUseCase(this.produtoGateway);

        cadastrarProdutoUseCase.execute(produtoForms);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<HttpStatus> atualizarProduto(@RequestBody @Valid ProdutoForms produtoForms){

        AtualizarProdutoUseCase atualizarProdutoUseCase =new AtualizarProdutoUseCase(this.produtoGateway);

        atualizarProdutoUseCase.execute(produtoForms);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){

        DeletarProdutoUseCase deletarProdutoUseCase = new DeletarProdutoUseCase(this.produtoGateway);

        deletarProdutoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
