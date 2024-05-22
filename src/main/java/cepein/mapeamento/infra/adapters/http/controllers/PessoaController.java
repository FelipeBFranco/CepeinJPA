package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.app.usecases.pessoa.*;
import cepein.mapeamento.infra.adapters.http.dtos.PessoaDto;
import cepein.mapeamento.infra.adapters.http.forms.PessoaForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.PessoaViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaGatway pessoagatway;

    public PessoaController(PessoaGatway pessoagatway) {
        this.pessoagatway = pessoagatway;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDto>> listarPessoas(){

        EncontrarListaDePessoaUseCase encontrarListaDePessoaUseCase = new EncontrarListaDePessoaUseCase(this.pessoagatway);
        List<PessoaDto> pessoaDtoList = encontrarListaDePessoaUseCase.encontrarListaPessoa()
                .stream()
                .map(PessoaViewModel::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoaDtoList);
    }

    @GetMapping("/procurar-por-id/{id}")
    public ResponseEntity<PessoaDto> procurarPessoa(@PathVariable Long id){

        EncontrarPessoaUseCase encontrarPessoaUseCase = new EncontrarPessoaUseCase(this.pessoagatway);

        PessoaDto pessoaDto = PessoaViewModel.toDto(encontrarPessoaUseCase.encontrarPessoa(id));
        return ResponseEntity.ok(pessoaDto);
    }

    @PostMapping("/cadastrar-pessoa-produto")
    public ResponseEntity<HttpStatus> cadastrarPessoaComProduto(@RequestBody @Valid PessoaForms pessoaForms){

        CadastrarPessoaUseCase cadastrarPessoaUseCase = new CadastrarPessoaUseCase(this.pessoagatway);

        cadastrarPessoaUseCase.criarPessoa(pessoaForms.converter());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<HttpStatus> atualizarPessoaExistente(@RequestBody PessoaForms pessoaForms, @PathVariable Long id){

        EncontrarPessoaUseCase encontrarPessoaUseCase = new EncontrarPessoaUseCase(this.pessoagatway);
        AtualizarPessoaUseCase atualizarPessoaUseCase = new AtualizarPessoaUseCase(this.pessoagatway);

        atualizarPessoaUseCase.atualizarPessoa(pessoaForms.converter(encontrarPessoaUseCase.encontrarPessoa(id)));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletarPessoa(@PathVariable Long id){

        DeletarPessoaUseCase deletarPessoaUseCase = new DeletarPessoaUseCase(this.pessoagatway);

        deletarPessoaUseCase.deletarPorId(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
