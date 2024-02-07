package cepein.mapeamento.pessoa.resource;

import cepein.mapeamento.pessoa.dto.PessoaDto;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @DeleteMapping("deletar/{idPessoa}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long idPessoa){
        return  this.pessoaService.deletarPessoa(idPessoa);
    }

}
