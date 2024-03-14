package cepein.mapeamento.endereco.service;

import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.forms.EnderecoForms;
import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.endereco.repository.EnderecoRepository;
import exception.ObjectNotFoundException;
import exception.PessoaExistException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco buscarEndereco(Long idEndereco){
        return this.enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado"));
    }

    public List<EnderecoDto> listarEnderecos(){
        List<Endereco> enderecoList = this.enderecoRepository.findAll();
        List<EnderecoDto> enderecoDtoList = EnderecoDto.converter(enderecoList);
        return enderecoDtoList;
    }

    public EnderecoDto procurarEndereco(Long idEndereco){
        Endereco endereco = this.buscarEndereco(idEndereco);
        EnderecoDto enderecoDto = new EnderecoDto(endereco);
        return enderecoDto;
    }

    @Transactional
    public Void cadastrarEndereco(EnderecoForms enderecoForms){

        Endereco endereco = enderecoForms.converter(new Endereco());
        this.enderecoRepository.save(endereco);
        return null;
    }
    @Transactional
    public Void alterarEndereco(Long idEndereco,EnderecoForms enderecoForms){
        Endereco endereco = this.buscarEndereco(idEndereco);
        Endereco enderecoAlterado = enderecoForms.converter(endereco);
        this.enderecoRepository.save(enderecoAlterado);
        return null;
    }

    @Transactional
    public ResponseEntity<Void> deletarEndereco(Long idEndereco){
        this.enderecoRepository.deleteById(idEndereco);
        return null;
    }
}
