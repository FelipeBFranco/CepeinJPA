package cepein.mapeamento.infra.adapters.http.controllers;

import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.app.usecases.curso.*;
import cepein.mapeamento.app.usecases.pessoa.EncontrarPessoaUseCase;
import cepein.mapeamento.infra.adapters.http.dtos.CursoDto;
import cepein.mapeamento.infra.adapters.http.forms.CursoForms;
import cepein.mapeamento.infra.adapters.http.viewmodels.CursoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoGateway cursoGateway;
    @Autowired
    public CursoController(CursoGateway cursoGateway){
        this.cursoGateway = cursoGateway;
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<CursoDto> listarCursoPorId(@PathVariable Long id){

        EncontrarCursoUseCase encontrarCursoUseCase = new EncontrarCursoUseCase(this.cursoGateway);
        CursoDto cursoDto = CursoViewModel.toDto(encontrarCursoUseCase.execute(id));

        return ResponseEntity.ok(cursoDto);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<CursoDto>> listarCursos(){

        EncontrarListaCursoUseCase encontrarListaCursoUseCase = new EncontrarListaCursoUseCase(this.cursoGateway);
        List<CursoDto> cursoDtoList = encontrarListaCursoUseCase.execute()
                .stream()
                .map(CursoViewModel::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cursoDtoList);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastraCurso(@RequestBody CursoForms cursoForms){

        CadastrarCursoUseCase cadastrarCursoUseCase = new CadastrarCursoUseCase(this.cursoGateway);

        cadastrarCursoUseCase.execute(cursoForms);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping ("/atualizar")
    public ResponseEntity<HttpStatus> alterarCurso(@RequestBody CursoForms cursoForms){

        AtualizarCursoUseCase atualizarCursoUseCase = new AtualizarCursoUseCase(this.cursoGateway);

        atualizarCursoUseCase.execute(cursoForms);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deleterCurso(@PathVariable Long id){

        DeletarCursoUseCase deletarCursoUseCase = new DeletarCursoUseCase(this.cursoGateway);

        deletarCursoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
