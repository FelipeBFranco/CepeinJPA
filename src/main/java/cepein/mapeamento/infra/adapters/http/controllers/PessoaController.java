package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.*;
import cepein.mapeamento.app.usecases.pessoa.*;
import cepein.mapeamento.infra.adapters.http.dtos.PessoaDto;
import cepein.mapeamento.infra.adapters.http.forms.PessoaForms;
import cepein.mapeamento.infra.adapters.http.forms.PessoaPedidoForms;
import cepein.mapeamento.infra.adapters.http.forms.PessoaProdutoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.PessoaViewModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaGatway pessoagatway;
    private  final ProdutoGateway produtoGateway;
    private  final PedidoGateway pedidoGateway;
    private final PessoaProdutoGateway pessoaProdutoGateway ;
    private  final PessoaPedidoGateway pessoaPedidoGateway ;
    @Autowired
    public PessoaController(PessoaGatway pessoagatway, ProdutoGateway produtoGateway, PedidoGateway pedidoGateway, PessoaProdutoGateway pessoaProdutoGateway, PessoaPedidoGateway pessoaPedidoGateway) {
        this.pessoagatway = pessoagatway;
        this.produtoGateway = produtoGateway;
        this.pedidoGateway = pedidoGateway;
        this.pessoaProdutoGateway = pessoaProdutoGateway;
        this.pessoaPedidoGateway = pessoaPedidoGateway;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PessoaDto> procurarPessoa(@PathVariable Long id){

        EncontrarPessoaUseCase encontrarPessoaUseCase = new EncontrarPessoaUseCase(this.pessoagatway);

        PessoaDto pessoaDto = PessoaViewModel.toDto(encontrarPessoaUseCase.execute(id));
        return ResponseEntity.ok(pessoaDto);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDto>> listarPessoas(){

        EncontrarListaDePessoaUseCase encontrarListaDePessoaUseCase = new EncontrarListaDePessoaUseCase(this.pessoagatway);
        List<PessoaDto> pessoaDtoList = encontrarListaDePessoaUseCase.execute()
                .stream()
                .map(PessoaViewModel::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoaDtoList);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarPessoa(@RequestBody @Valid PessoaForms pessoaForms){

        CadastrarPessoaUseCase cadastrarPessoaUseCase = new CadastrarPessoaUseCase(this.pessoagatway);

        cadastrarPessoaUseCase.execute(pessoaForms);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/cadastrar-pessoa-produto/{idPessoa}")
    public ResponseEntity<HttpStatus> cadastrarPessoaProduto(@RequestBody PessoaProdutoForms pessoaProdutoForms){

        CadastrarPessoaProdutoUseCase cadastrarPessoaProdutoUseCase =
                new CadastrarPessoaProdutoUseCase(this.pessoagatway,this.produtoGateway, this.pessoaProdutoGateway);
        cadastrarPessoaProdutoUseCase.execute(pessoaProdutoForms);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/cadastrar-pessoa-pedido")
    public ResponseEntity<HttpStatus> cadastrarPessoaPedido(@RequestBody PessoaPedidoForms pessoaPedidoForms){

        CadastrarPessoaPedidoUseCase cadastrarPessoaPedidoUseCase =
                new CadastrarPessoaPedidoUseCase(this.pessoagatway,this.pedidoGateway, this.pessoaPedidoGateway);

        cadastrarPessoaPedidoUseCase.execute(pessoaPedidoForms);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/atualizar")
    public ResponseEntity<HttpStatus> atualizarPessoaExistente(@RequestBody @Valid PessoaForms pessoaForms){

        AtualizarPessoaUseCase atualizarPessoaUseCase = new AtualizarPessoaUseCase(this.pessoagatway);

        atualizarPessoaUseCase.execute(pessoaForms);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletarPessoa(@PathVariable Long id){

        DeletarPessoaUseCase deletarPessoaUseCase = new DeletarPessoaUseCase(this.pessoagatway);

        deletarPessoaUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
