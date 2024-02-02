package cepein.mapeamento.pessoa.resource;

import cepein.mapeamento.pessoa.dto.PessoaDto;
import cepein.mapeamento.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
