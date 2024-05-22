package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.app.usecases.curso.*;
import cepein.mapeamento.app.usecases.pessoa.EncontrarPessoaUseCase;
import cepein.mapeamento.infra.adapters.http.dtos.CursoDto;
import cepein.mapeamento.infra.adapters.http.forms.CursoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.CursoViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoGateway cursoGateway;
    private final PessoaGatway pessoaGatway;

    public CursoController(CursoGateway cursoGateway, PessoaGatway pessoaGatway){
        this.cursoGateway = cursoGateway;
        this.pessoaGatway = pessoaGatway;
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<CursoDto> listarCursoPorId(@PathVariable Long id){

        EncontrarCursoUseCase encontrarCursoUseCase = new EncontrarCursoUseCase(this.cursoGateway);
        CursoDto cursoDto = CursoViewModel.toDto(encontrarCursoUseCase.encontrarCurso(id));

        return ResponseEntity.ok(cursoDto);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<CursoDto>> listarCursos(){

        EncontrarListaCursoUseCase encontrarListaCursoUseCase = new EncontrarListaCursoUseCase(this.cursoGateway);
        List<CursoDto> cursoDtoList = encontrarListaCursoUseCase.encontrarListaCurso()
                .stream()
                .map(CursoViewModel::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(cursoDtoList);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastraEndereco(@RequestBody CursoForms cursoForms){

        CadastrarCursoUseCase cadastrarCursoUseCase = new CadastrarCursoUseCase(this.cursoGateway);
        EncontrarPessoaUseCase encontrarPessoaUseCase =new EncontrarPessoaUseCase(this.pessoaGatway);

        cadastrarCursoUseCase.criarCurso(cursoForms.converter(encontrarPessoaUseCase.encontrarPessoa(cursoForms.getPessoaId())));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping ("/atualizar/{id}")//no forms o id
    public ResponseEntity<HttpStatus> alterarCurso(@PathVariable Long id,@RequestBody CursoForms cursoForms){

        EncontrarCursoUseCase encontrarCursoUseCase = new EncontrarCursoUseCase(this.cursoGateway);
        AtualizarCursoUseCase atualizarCursoUseCase = new AtualizarCursoUseCase(this.cursoGateway);
        EncontrarPessoaUseCase encontrarPessoaUseCase =new EncontrarPessoaUseCase(this.pessoaGatway);

        atualizarCursoUseCase.atualizarCurso(cursoForms.converter(
                encontrarCursoUseCase.encontrarCurso(id),
                encontrarPessoaUseCase.encontrarPessoa(cursoForms.getPessoaId())));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deleterCurso(@PathVariable Long id){

        DeletarCursoUseCase deletarCursoUseCase = new DeletarCursoUseCase(this.cursoGateway);

        deletarCursoUseCase.deletarCurso(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
