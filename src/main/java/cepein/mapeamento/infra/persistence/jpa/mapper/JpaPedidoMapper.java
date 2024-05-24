package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.pedido.PedidoCommand;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.infra.persistence.jpa.entities.pedido.JpaPedidoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pedido.JpaPedidoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoQueryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JpaPedidoMapper {
    public static JpaPedidoCommandEntity toEntity(PedidoCommand pedidoCommand){
        return JpaPedidoCommandEntity.builder()
                .id(pedidoCommand.getId())
                .uuid(pedidoCommand.getUuid())
                .descricao(pedidoCommand.getDescricao())
                .build();
    }

    public static PedidoQuery toDomain(JpaPedidoQueryEntity jpaPedidoQueryEntity){
        return PedidoQuery.builder()
                .id(jpaPedidoQueryEntity.getId())
                .uuid(jpaPedidoQueryEntity.getUuid())
                .descricao(jpaPedidoQueryEntity.getDescricao())
                .pessoaQueryListComJoinTable(JpaPedidoMapper.pessoaDePedidoToDomain(jpaPedidoQueryEntity.getPessoaListComJoinTable()))
                .pessoaQueryListComEmbeddable(JpaPedidoMapper.pessoaProdutoDePedidoToDomain(jpaPedidoQueryEntity.getPessoaListComEmbeddable()))
                .build();
    }
    public static List<PedidoQuery> toDomain(List<JpaPedidoQueryEntity> jpaPedidoQueryEntityList){
        return jpaPedidoQueryEntityList.stream()
                .map(JpaPedidoMapper::toDomain)
                .collect(Collectors.toList());
    }
    public static List<PessoaQuery> pessoaDePedidoToDomain(List<JpaPessoaQueryEntity> jpaPessoaQueryEntityList){

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
                        .produtoQueryListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaEntity.getProdutoListComJoinTable()))
                        .produtoQueryListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaEntity.getProdutoListComEmbeddable()))
                        .build() )
                .collect(Collectors.toList());
    }
    public static List<PessoaQuery> pessoaProdutoDePedidoToDomain(List<JpaPessoaPedidoQueryEntity> jpaPessoaProdutoEntityEntityList){
        List<JpaPessoaQueryEntity> pessoaEntityList = jpaPessoaProdutoEntityEntityList
                .stream()
                .map(JpaPessoaPedidoQueryEntity::getPessoa)
                .collect(Collectors.toList());
        return JpaProdutoMapper.pessoaDeProdutoToDomain(pessoaEntityList);
    }
}
