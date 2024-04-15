package cepein.mapeamento.infra.service;

import cepein.mapeamento.infra.dto.EnderecoDto;
import cepein.mapeamento.infra.forms.EnderecoForms;
import cepein.mapeamento.model.Endereco;
import cepein.mapeamento.infra.repository.EnderecoRepository;
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
    public void   cadastrarEndereco(EnderecoForms enderecoForms){

        Endereco endereco = enderecoForms.converter(new Endereco());
        this.enderecoRepository.save(endereco);
    }
    @Transactional
    public void  alterarEndereco(Long idEndereco,EnderecoForms enderecoForms){
        Endereco endereco = this.buscarEndereco(idEndereco);
        Endereco enderecoAlterado = enderecoForms.converter(endereco);
        this.enderecoRepository.save(enderecoAlterado);

    }

    @Transactional
    public void deletarEndereco(Long idEndereco){
        Endereco endereco = this.buscarEndereco(idEndereco);
        if(endereco.getPessoaPorId() != null && endereco.getPessoaPorUuid() != null){
            throw new PessoaExistException("Uma pessoa possui este endereço, não é possivel deleta-lo");
        }
        this.enderecoRepository.deleteById(idEndereco);

    }
}
