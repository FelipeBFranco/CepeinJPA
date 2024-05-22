package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.*;
import cepein.mapeamento.infra.persistence.jpa.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class JpaPessoaMapper {
    public static JpaPessoaEntity toEntity(Pessoa pessoaDomain){
        return JpaPessoaEntity.builder()
                .id(pessoaDomain.getId())
                .uuid(pessoaDomain.getUuid())
                .nome(pessoaDomain.getNome())
                .enderecoPorId(JpaEnderecoMapper.toEntity(pessoaDomain.getEnderecoPorId()))
                .enderecoPorUuid(JpaEnderecoMapper.toEntity(pessoaDomain.getEnderecoPorUuid()))
                .build();
    }
    public static Pessoa toDomain(JpaPessoaEntity jpaPessoaEntity){
        return Pessoa.builder()
                .id(jpaPessoaEntity.getId())
                .uuid(jpaPessoaEntity.getUuid())
                .nome(jpaPessoaEntity.getNome())
                .enderecoPorId(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorId()))
                .enderecoPorUuid(JpaPessoaMapper.toDomain(jpaPessoaEntity.getEnderecoPorUuid()))
                .cursoPorId(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorId()))
                .cursoPorUuid(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorUuid()))
                .produtoListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaEntity.getProdutoListComJoinTable()))
                .produtoListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaEntity.getProdutoListComEmbeddable()))
                .pedidoListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComJoinTable()))
                .pedidoListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComEmbeddable()))
                .build();
    }

    public  static Endereco toDomain(JpaEnderecoEntity jpaEnderecoEntity){
        return Endereco.builder()
                .id_endereco(jpaEnderecoEntity.getId_endereco())
                .uuid(jpaEnderecoEntity.getUuid())
                .cep(jpaEnderecoEntity.getCep())
                .cidade(jpaEnderecoEntity.getCidade())
                .estado(jpaEnderecoEntity.getEstado())
                .build();
    }
    public static List<Curso> cursoDePessoaToDomain(List<JpaCursoEntity> jpaCursoEntityList){
        return jpaCursoEntityList
                .stream()
                .map(curso->Curso.builder()
                        .id(curso.getId())
                        .descricao(curso.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<Produto> produtoDePessoatoDomain(List<JpaProdutoEntity> jpaProdutoEntityList){
        return jpaProdutoEntityList
                .stream()
                .map(jpaProdutoEntity -> Produto.builder()
                        .id(jpaProdutoEntity.getId())
                        .descricao(jpaProdutoEntity.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }
    public static List<Produto> pessoaProdutoDePessoaToDomain(List<JpaPessoaProdutoEntity> jpaPessoaProdutoEntityEntityList){
        List<JpaProdutoEntity> jpaProdutoEntityList = jpaPessoaProdutoEntityEntityList
                .stream()
                .map(JpaPessoaProdutoEntity::getProduto)
                .collect(Collectors.toList());
        return JpaPessoaMapper.produtoDePessoatoDomain(jpaProdutoEntityList);
    }
    public static List<Pedido> pedidoDePessoaToDomain(List<JpaPedidoEntity> jpaPedidoEntityList){
        return jpaPedidoEntityList.stream()
                .map(jpaPedidoEntity ->  Pedido.builder()
                        .id(jpaPedidoEntity.getId())
                        .uuid(jpaPedidoEntity.getUuid())
                        .descricao(jpaPedidoEntity.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }
    public static List<Pedido> pessoaPedidoDePessoaToDomain(List<JpaPessoaPedidoEntity> jpaPessoaPedidoEntityList){
        List<JpaPedidoEntity> jpaPedidoEntityList = jpaPessoaPedidoEntityList
                .stream()
                .map(JpaPessoaPedidoEntity::getPedido)
                .collect(Collectors.toList());
        return JpaPessoaMapper.pedidoDePessoaToDomain(jpaPedidoEntityList);
    }
}
