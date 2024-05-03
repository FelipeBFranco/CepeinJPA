package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaCursoEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JpaCursoMapper {

    public static JpaCursoEntity toEntity(Curso cursoDomain){
        return JpaCursoEntity.builder()
                .id(cursoDomain.getId())
                .descricao(cursoDomain.getDescricao())
                .pessoaPorId(JpaPessoaMapper.toEntity(cursoDomain.getPessoaPorId()))
                .pessoaPorUuid(JpaPessoaMapper.toEntity(cursoDomain.getPessoaPorUuid()))
                .build();
    }
    public static Curso toDomain(JpaCursoEntity jpaCursoEntity){
        return Curso.builder()
                .id(jpaCursoEntity.getId())
                .descricao(jpaCursoEntity.getDescricao())
                .pessoaPorId(JpaCursoMapper.toDomain(jpaCursoEntity.getPessoaPorId()))
                .pessoaPorUuid(JpaCursoMapper.toDomain(jpaCursoEntity.getPessoaPorUuid()))
                .build();
    }
    public static Pessoa toDomain(JpaPessoaEntity jpaPessoaEntity){
        return Pessoa.builder()
                .id(jpaPessoaEntity.getId())
                .uuid(jpaPessoaEntity.getUuid())
                .nome(jpaPessoaEntity.getNome())
                .enderecoPorId(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorId()))
                .enderecoPorUuid(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorUuid()))
                .produtoListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaEntity.getProdutoListComJoinTable()))
                .produtoListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaEntity.getProdutoListComEmbeddable()))
                .pedidoListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComJoinTable()))
                .pedidoListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComEmbeddable()))
                .build();
    }


}
