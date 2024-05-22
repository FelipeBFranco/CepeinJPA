package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.Pedido;
import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPedidoEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaPedidoEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaProdutoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JpaPedidoMapper {
    public static JpaPedidoEntity toEntity(Pedido pedidoDomain){
        return JpaPedidoEntity.builder()
                .id(pedidoDomain.getId())
                .uuid(pedidoDomain.getUuid())
                .descricao(pedidoDomain.getDescricao())
                .build();
    }

    public static Pedido toDomain(JpaPedidoEntity jpaPedidoEntity){
        return Pedido.builder()
                .id(jpaPedidoEntity.getId())
                .uuid(jpaPedidoEntity.getUuid())
                .descricao(jpaPedidoEntity.getDescricao())
                .pessoaListComJoinTable(JpaPedidoMapper.pessoaDePedidoToDomain(jpaPedidoEntity.getPessoaListComJoinTable()))
                .pessoaListComEmbeddable(JpaPedidoMapper.pessoaProdutoDePedidoToDomain(jpaPedidoEntity.getPessoaListComEmbeddable()))
                .build();
    }
    public static List<Pedido> toDomain(List<JpaPedidoEntity> jpaPedidoEntityList){
        return jpaPedidoEntityList.stream()
                .map(JpaPedidoMapper::toDomain)
                .collect(Collectors.toList());
    }
    public static List<Pessoa> pessoaDePedidoToDomain(List<JpaPessoaEntity> jpaPessoaEntityList){

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
                        .produtoListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaEntity.getProdutoListComJoinTable()))
                        .produtoListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaEntity.getProdutoListComEmbeddable()))
                        .build() )
                .collect(Collectors.toList());
    }
    public static List<Pessoa> pessoaProdutoDePedidoToDomain(List<JpaPessoaPedidoEntity> jpaPessoaProdutoEntityEntityList){
        List<JpaPessoaEntity> pessoaEntityList = jpaPessoaProdutoEntityEntityList
                .stream()
                .map(JpaPessoaPedidoEntity::getPessoa)
                .collect(Collectors.toList());
        return JpaProdutoMapper.pessoaDeProdutoToDomain(pessoaEntityList);
    }
}
