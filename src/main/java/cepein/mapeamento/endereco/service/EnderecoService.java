package cepein.mapeamento.endereco.service;

import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.forms.EnderecoForms;
import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.endereco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<EnderecoDto> procurarEndereco(Long idEndereco){
        Endereco endereco = this.enderecoRepository.findById(idEndereco)
                .orElseThrow(()-> new RuntimeException("endereco não encontrado"));
        EnderecoDto enderecoDto = new EnderecoDto(endereco);
        return ResponseEntity.ok(enderecoDto);
    }
    public ResponseEntity<Void> cadastrarEndereco(EnderecoForms enderecoForms){
        Endereco endereco = enderecoForms.convet(new Endereco());
        this.enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public ResponseEntity<Void> alterarEndereco(Long idEndereco,EnderecoForms enderecoForms){
        Endereco endereco = this.enderecoRepository.findById(idEndereco)
                .orElseThrow(()-> new RuntimeException("endereco não encontrado"));
        Endereco enderecoAlterado = enderecoForms.convet(endereco);
        this.enderecoRepository.save(enderecoAlterado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public ResponseEntity<Void> deletarEndereco(Long idEndereco){
        this.enderecoRepository.deleteById(idEndereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
