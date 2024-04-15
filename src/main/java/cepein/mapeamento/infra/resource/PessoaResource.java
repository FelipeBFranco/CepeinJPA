package cepein.mapeamento.infra.resource;

import cepein.mapeamento.infra.dto.PessoaDto;
import cepein.mapeamento.infra.forms.PessoaForms;
import cepein.mapeamento.infra.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    private final PessoaService pessoaService;
    @Autowired
    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDto>> listarPessoas(){
        return ResponseEntity.ok(this.pessoaService.listarPessoas());
    }

    @GetMapping("/procurar-por-id/{idPessoa}")
    public ResponseEntity<PessoaDto> procurarPessoa(@PathVariable Long idPessoa){
        return ResponseEntity.ok(this.pessoaService.procurarPessoa(idPessoa));
    }

    @PostMapping("/cadastrar-pessoa-produto")
    public ResponseEntity<HttpStatus> cadastrarPessoaComProduto(@RequestBody @Valid PessoaForms pessoaForms){
        this.pessoaService.cadastrarPessoaComProduto(pessoaForms);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar/{idPessoa}")
    public ResponseEntity<HttpStatus> atualizarPessoaExistente(@RequestBody PessoaForms pessoaForms, @PathVariable Long idPessoa){
        this.pessoaService.atualizarPessoa(pessoaForms, idPessoa);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deletar/{idPessoa}")
    public ResponseEntity<HttpStatus> deletarPessoa(@PathVariable Long idPessoa){

        this.pessoaService.deletarPessoa(idPessoa);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
