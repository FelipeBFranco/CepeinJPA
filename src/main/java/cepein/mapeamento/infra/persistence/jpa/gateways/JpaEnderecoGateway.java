package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.infra.persistence.jpa.repositories.EnderecoRepository;
import cepein.mapeamento.acore.domain.models.Endereco;

import cepein.mapeamento.infra.persistence.jpa.mapper.JpaEnderecoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaEnderecoGateway implements EnderecoGateway {

    private EnderecoRepository enderecoRepository;
    @Autowired
    public JpaEnderecoGateway(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Endereco encontrarEnderecoPorId(Long id) {
        return JpaEnderecoMapper.toDomain( this.enderecoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado")));
    }

    @Override
    public List<Endereco> encontrarTodosOsEnderecos() {
        return this.enderecoRepository.findAll()
                .stream()
                .map(JpaEnderecoMapper::toDomain)
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void salvarEndereco(Endereco endereco) {
        this.enderecoRepository.save(JpaEnderecoMapper.toEntity(endereco));
    }
    @Transactional
    @Override
    public void deletarEnderecoPorId(Long id) {
        System.out.println("impGateway");
        this.enderecoRepository.deleteById(id);
    }


}
