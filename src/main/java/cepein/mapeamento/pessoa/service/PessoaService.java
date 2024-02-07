package cepein.mapeamento.pessoa.service;

import cepein.mapeamento.curso.dto.CursoDto;
import cepein.mapeamento.curso.model.Curso;
import cepein.mapeamento.pessoa.dto.PessoaDto;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public Pessoa buscarPessoa(Long idPessoa){
        return this.pessoaRepository.findById(idPessoa)
                .orElseThrow(()-> new ObjectNotFoundException("Pessoa não encontrada"));
    }

    public ResponseEntity<List<PessoaDto>> listarPessoas(){
        List<Pessoa> pessoaList = this.pessoaRepository.findAll();
        List<PessoaDto> pessoaDtoList = PessoaDto.converter(pessoaList);
        return ResponseEntity.ok(pessoaDtoList);
    }
    public ResponseEntity<PessoaDto> procurarPessoa(Long idPessoa){
        Pessoa pessoa = this.buscarPessoa(idPessoa);
        PessoaDto pessoaDto = new PessoaDto(pessoa);

        return ResponseEntity.ok(pessoaDto);
    }
    @Transactional
    public ResponseEntity<Void> deletarPessoa(Long idPessoa){
        this.pessoaRepository.deleteById(idPessoa);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
