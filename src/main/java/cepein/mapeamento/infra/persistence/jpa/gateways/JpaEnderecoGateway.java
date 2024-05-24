package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoCommand;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.infra.persistence.jpa.repositories.endereco.EnderecoCommandRepository;
import cepein.mapeamento.infra.persistence.jpa.repositories.endereco.EnderecoQueryRepository;

import cepein.mapeamento.infra.persistence.jpa.mapper.JpaEnderecoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaEnderecoGateway implements EnderecoGateway {
    private EnderecoQueryRepository enderecoQueryRepository;
    private EnderecoCommandRepository enderecoCommandRepository;
    @Autowired
    public JpaEnderecoGateway(EnderecoQueryRepository enderecoQueryRepository, EnderecoCommandRepository enderecoCommandRepository){
        this.enderecoQueryRepository = enderecoQueryRepository;
        this.enderecoCommandRepository = enderecoCommandRepository;
    }

    @Override
    public void salvar(EnderecoCommand enderecoCommand) {
        this.enderecoCommandRepository.save(JpaEnderecoMapper.toEntity(enderecoCommand));
    }

    @Override
    public void deletar(Long id) {

        this.enderecoCommandRepository.deleteById(id);
    }

    @Override
    public EnderecoQuery buscar(Long id) {
        return JpaEnderecoMapper.toDomain( this.enderecoQueryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado")));
    }
    @Override
    public List<EnderecoQuery> encontrarTodosOsEnderecos() {
        return this.enderecoQueryRepository.findAll()
                .stream()
                .map(JpaEnderecoMapper::toDomain)
                .collect(Collectors.toList());
    }
}
