package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.models.produto.ProdutoCommand;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.produto.JpaProdutoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.produto.JpaProdutoQueryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JpaProdutoMapper {
    public static JpaProdutoCommandEntity toEntity(ProdutoCommand produtoCommand){
        return JpaProdutoCommandEntity.builder()
                .id(produtoCommand.getId())
                .descricao(produtoCommand.getDescricao())
                .build();
    }
    public  static ProdutoQuery toDomain(JpaProdutoQueryEntity jpaProdutoQueryEntity){
        return ProdutoQuery.builder()
                .id(jpaProdutoQueryEntity.getId())
                .descricao(jpaProdutoQueryEntity.getDescricao())
                .pessoaQueryListComJoinTable(JpaProdutoMapper.pessoaDeProdutoToDomain(jpaProdutoQueryEntity.getPessoaListComJoinTable()))
                .pessoaQueryListComEmbeddable(JpaProdutoMapper.pessoaProdutoDeProdutoToDomain(jpaProdutoQueryEntity.getPessoaListComEmbeddable()))
                .build();
    }

    public static List<PessoaQuery> pessoaDeProdutoToDomain(List<JpaPessoaQueryEntity> jpaPessoaQueryEntityList){
        return jpaPessoaQueryEntityList
                .stream()
                .map(jpaPessoaEntity -> PessoaQuery.builder()
                        .id(jpaPessoaEntity.getId())
                        .uuid(jpaPessoaEntity.getUuid())
                        .nome(jpaPessoaEntity.getNome())
                        .enderecoQueryPorId(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorId()))
                        .enderecoQueryPorUuid(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorUuid()))
                        .cursoPorId(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorId()))
                        .cursoPorUuid(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorUuid()))
                        .pedidoQueryListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComJoinTable()))
                        .pedidoQueryListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComEmbeddable()))
                        .build() )
                .collect(Collectors.toList());
    }
    public static List<PessoaQuery> pessoaProdutoDeProdutoToDomain(List<JpaPessoaProdutoQueryEntity> jpaPessoaProdutoEntityQueryEntityList){
        List<JpaPessoaQueryEntity> pessoaEntityList = jpaPessoaProdutoEntityQueryEntityList
                .stream()
                .map(JpaPessoaProdutoQueryEntity::getPessoa)
                .collect(Collectors.toList());
        return JpaProdutoMapper.pessoaDeProdutoToDomain(pessoaEntityList);
    }
}
