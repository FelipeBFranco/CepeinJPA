package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.acore.domain.models.curso.CursoCommand;
import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.infra.persistence.jpa.repositories.curso.CursoCommandRepository;
import cepein.mapeamento.infra.persistence.jpa.repositories.curso.CursoQueryRepository;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaCursoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaCursoGateway implements CursoGateway {
    private final CursoQueryRepository cursoQueryRepository;
    private final CursoCommandRepository cursoCommandRepository;
    @Autowired
    public JpaCursoGateway(CursoQueryRepository cursoQueryRepository, CursoCommandRepository cursoCommandRepository) {
        this.cursoQueryRepository = cursoQueryRepository;
        this.cursoCommandRepository = cursoCommandRepository;
    }
    @Transactional
    @Override
    public void salvar(CursoCommand cursoCommand) {
        this.cursoCommandRepository.save(JpaCursoMapper.toEntity(cursoCommand));
    }
    @Transactional
    @Override
    public void deletar(Long id) {
        this.cursoCommandRepository.deleteById(id);
    }

    @Override
    public CursoQuery buscar(Long id) {
        return JpaCursoMapper.toDomain(this.cursoQueryRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Curso não encontrado")));
    }
    @Override
    public List<CursoQuery> encontrarTodosOsCursos() {
        return this.cursoQueryRepository.findAll()
                .stream()
                .map(JpaCursoMapper::toDomain)
                .collect(Collectors.toList());
    }


}
