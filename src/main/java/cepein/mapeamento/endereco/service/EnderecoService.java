package cepein.mapeamento.endereco.service;

import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.endereco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public ResponseEntity<List<EnderecoDto>> listarEnderecos(){
        List<Endereco> enderecoList = this.enderecoRepository.findAll();
        List<EnderecoDto> enderecoDtoList = EnderecoDto.convet(enderecoList);
        return ResponseEntity.ok(enderecoDtoList);
    }
}
