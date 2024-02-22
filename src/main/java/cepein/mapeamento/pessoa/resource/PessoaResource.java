package cepein.mapeamento.pessoa.resource;

import cepein.mapeamento.pessoa.dto.PessoaDto;
import cepein.mapeamento.pessoa.forms.PessoaForms;
import cepein.mapeamento.pessoa.service.PessoaService;
import cepein.mapeamento.produto.forms.ProdutoForms;
import cepein.mapeamento.produto.model.Produto;
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
        return this.pessoaService.listarPessoas();
    }

    @GetMapping("/procurar-por-id/{idPessoa}")
    public ResponseEntity<PessoaDto> procurarPessoa(@PathVariable Long idPessoa){
        return this.pessoaService.procurarPessoa(idPessoa);
    }

    @PostMapping("/cadastrar-pessoa-produto")
    public ResponseEntity<HttpStatus> cadastrarPessoaComProduto(@RequestBody @Valid PessoaForms pessoaForms){
        return this.pessoaService.cadastrarPessoaComProduto(pessoaForms);
    }

    @PutMapping("/atualizar/{idPessoa}")
    public ResponseEntity<HttpStatus> atualizarPessoaExistente(@RequestBody PessoaForms pessoaForms, @PathVariable Long idPessoa){
        return this.pessoaService.atualizarPessoaExistente(pessoaForms, idPessoa);
    }

    @DeleteMapping("/deletar/{idPessoa}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long idPessoa){
        return  this.pessoaService.deletarPessoa(idPessoa);
    }

}
