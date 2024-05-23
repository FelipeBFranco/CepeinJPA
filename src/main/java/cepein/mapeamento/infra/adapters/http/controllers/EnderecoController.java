package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.app.usecases.endereco.*;
import cepein.mapeamento.infra.adapters.http.dtos.EnderecoDto;
import cepein.mapeamento.infra.adapters.http.forms.EnderecoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.EnderecoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoGateway enderecoGateway;

    @Autowired
    EnderecoController (EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<EnderecoDto> buscarEnderecoPorId(@PathVariable Long id){
        EncontrarEnderecoUseCase encontrarEnderecoUseCase = new EncontrarEnderecoUseCase(this.enderecoGateway);
        EnderecoDto enderecoDto = EnderecoViewModel.toDto(encontrarEnderecoUseCase.execute(id)) ;
        return ResponseEntity.ok(enderecoDto);

    }
    @GetMapping("/listar")
    public ResponseEntity<List<EnderecoDto>> listarEndereco(){

        EncontrarListaEnderecoUseCase encontrarListaEnderecoUseCase = new EncontrarListaEnderecoUseCase(this.enderecoGateway);
        List<EnderecoDto> enderecoDtoList =  encontrarListaEnderecoUseCase.execute()
                .stream()
                .map(EnderecoViewModel::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(enderecoDtoList);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> criarEndereco(@RequestBody EnderecoForms enderecoForms){

        CadastrarEnderecoUseCase cadastrarEnderecoUseCase = new CadastrarEnderecoUseCase(this.enderecoGateway);
        cadastrarEnderecoUseCase.execute(enderecoForms);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PatchMapping("/atualizar")
    public ResponseEntity<HttpStatus> atualizarEndereco(@RequestBody EnderecoForms enderecoForms){

        AtualizarEnderecoUseCase atualizarEnderecoUseCase = new AtualizarEnderecoUseCase(this.enderecoGateway);
        atualizarEnderecoUseCase.execute(enderecoForms);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletarEndereco(@PathVariable Long id){

        DeletarEnderecoUseCase deletarEnderecoUseCase = new DeletarEnderecoUseCase(this.enderecoGateway);
        deletarEnderecoUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
