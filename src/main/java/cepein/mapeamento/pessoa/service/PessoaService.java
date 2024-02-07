package cepein.mapeamento.pessoa.service;

import cepein.mapeamento.pessoa.dto.PessoaDto;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository ;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    public ResponseEntity<List<PessoaDto>> listarPessoas(){
        List<Pessoa> pessoaList = this.pessoaRepository.findAll();
        List<PessoaDto> pessoaDtoList = PessoaDto.converter(pessoaList);
        return ResponseEntity.ok(pessoaDtoList);
    }
}
