package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaCommand;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaPessoaMapper;
import cepein.mapeamento.infra.persistence.jpa.repositories.pessoa.PessoaCommandRepository;
import cepein.mapeamento.infra.persistence.jpa.repositories.pessoa.PessoaQueryRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaPessoaGatway implements PessoaGatway {
    private final PessoaQueryRepository pessoaQueryRepository;
    private final PessoaCommandRepository pessoaCommandRepository;

    public JpaPessoaGatway(PessoaQueryRepository pessoaQueryRepository, PessoaCommandRepository pessoaCommandRepository) {
        this.pessoaQueryRepository = pessoaQueryRepository;
        this.pessoaCommandRepository = pessoaCommandRepository;
    }


    @Transactional
    @Override
    public void salvar(PessoaCommand pessoaCommand) {
        this.pessoaCommandRepository.save(JpaPessoaMapper.toEntity(pessoaCommand));
    }
    @Transactional
    @Override
    public void deletar(Long id) {
        this.pessoaCommandRepository.deleteById(id);
    }

    @Override
    public PessoaQuery buscar(Long id) {
        return JpaPessoaMapper.toDomain(this.pessoaQueryRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("PessoaQuery não encontrada")));
    }
    @Override
    public PessoaQuery buscar(String uuid) {
        return JpaPessoaMapper.toDomain(this.pessoaQueryRepository.findByUuid(uuid)
                .orElseThrow(()->new ObjectNotFoundException("PessoaQuery não encontrada")));
    }

    @Override
    public List<PessoaQuery> encontrarTodasAsPessoa() {
        return this.pessoaQueryRepository.findAll()
                .stream()
                .map(JpaPessoaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
