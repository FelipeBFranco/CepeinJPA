package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.infra.persistence.jpa.repositories.PessoaRepository;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaPessoaMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaPessoaGatway implements PessoaGatway {
    private final PessoaRepository pessoaRepository;

    public JpaPessoaGatway(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa encontrarPessoaPorId(Long id) {
        return JpaPessoaMapper.toDomain(this.pessoaRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("Pessoa não encontrada")));
    }

    @Override
    public List<Pessoa> encontrarTodasAsPessoa() {
        return this.pessoaRepository.findAll()
                .stream()
                .map(JpaPessoaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void salvarPessoa(Pessoa pessoa) {
        this.pessoaRepository.save(JpaPessoaMapper.toEntity(pessoa));
    }

    @Transactional
    @Override
    public void deletarPessoaPorId(Long id) {
        this.pessoaRepository.deleteById(id);
    }
}
