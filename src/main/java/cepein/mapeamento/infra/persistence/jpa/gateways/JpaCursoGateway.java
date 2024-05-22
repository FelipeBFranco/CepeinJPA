package cepein.mapeamento.infra.persistence.jpa.gateways;

import cepein.mapeamento.app.gateways.CursoGateway;
import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.infra.persistence.jpa.repositories.CursoRepository;
import cepein.mapeamento.infra.persistence.jpa.mapper.JpaCursoMapper;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaCursoGateway implements CursoGateway {
    private final CursoRepository cursoRepository;
    @Autowired
    public JpaCursoGateway(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso encontrarCursoPorId(Long id) {
        return JpaCursoMapper.toDomain(this.cursoRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Curso não encontrado")));
    }

    @Override
    public List<Curso> encontrarTodosOsCursos() {
        return this.cursoRepository.findAll()
                .stream()
                .map(JpaCursoMapper::toDomain)
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void salvarCurso(Curso curso) {
        this.cursoRepository.save(JpaCursoMapper.toEntity(curso));
    }
    @Transactional
    @Override
    public void deletarCursoPorId(Long id) {
        this.cursoRepository.deleteById(id);
    }
}
