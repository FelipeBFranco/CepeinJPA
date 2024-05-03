package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.acore.domain.models.Produto;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaProdutoEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaProdutoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JpaProdutoMapper {
    public static JpaProdutoEntity toEntity(Produto produtoDomain){
        return JpaProdutoEntity.builder()
                .id(produtoDomain.getId())
                .descricao(produtoDomain.getDescricao())
                .build();
    }
    public  static Produto toDomain(JpaProdutoEntity jpaProdutoEntity){
        return Produto.builder()
                .id(jpaProdutoEntity.getId())
                .descricao(jpaProdutoEntity.getDescricao())
                .pessoaListComJoinTable(JpaProdutoMapper.pessoaDeProdutoToDomain(jpaProdutoEntity.getPessoaListComJoinTable()))
                .pessoaListComEmbeddable(JpaProdutoMapper.pessoaProdutoDeProdutoToDomain(jpaProdutoEntity.getPessoaListComEmbeddable()))
                .build();
    }

    public static List<Pessoa> pessoaDeProdutoToDomain(List<JpaPessoaEntity> jpaPessoaEntityList){
        return jpaPessoaEntityList
                .stream()
                .map(jpaPessoaEntity ->Pessoa.builder()
                        .id(jpaPessoaEntity.getId())
                        .uuid(jpaPessoaEntity.getUuid())
                        .nome(jpaPessoaEntity.getNome())
                        .enderecoPorId(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorId()))
                        .enderecoPorUuid(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorUuid()))
                        .cursoPorId(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorId()))
                        .cursoPorUuid(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorUuid()))
                        .pedidoListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComJoinTable()))
                        .pedidoListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComEmbeddable()))
                        .build() )
                .collect(Collectors.toList());
    }
    public static List<Pessoa> pessoaProdutoDeProdutoToDomain(List<JpaPessoaProdutoEntity> jpaPessoaProdutoEntityEntityList){
        List<JpaPessoaEntity> pessoaEntityList = jpaPessoaProdutoEntityEntityList
                .stream()
                .map(JpaPessoaProdutoEntity::getPessoa)
                .collect(Collectors.toList());
        return JpaProdutoMapper.pessoaDeProdutoToDomain(pessoaEntityList);
    }
}
