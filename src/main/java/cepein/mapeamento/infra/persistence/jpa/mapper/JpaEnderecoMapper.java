package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.Endereco;
import cepein.mapeamento.acore.domain.models.Pessoa;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaEnderecoEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.JpaPessoaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JpaEnderecoMapper {

    public static JpaEnderecoEntity toEntity(Endereco enderecoDomain){
        return JpaEnderecoEntity.builder()
                .id_endereco(enderecoDomain.getId_endereco())
                .uuid(enderecoDomain.getUuid())
                .rua(enderecoDomain.getRua())
                .cep(enderecoDomain.getCep())
                .cidade(enderecoDomain.getCidade())
                .estado(enderecoDomain.getEstado())
                .build();
    }
  public  static Endereco toDomain(JpaEnderecoEntity jpaEnderecoEntity){
      return Endereco.builder()
              .id_endereco(jpaEnderecoEntity.getId_endereco())
              .uuid(jpaEnderecoEntity.getUuid())
              .rua(jpaEnderecoEntity.getRua())
              .cep(jpaEnderecoEntity.getCep())
              .cidade(jpaEnderecoEntity.getCidade())
              .estado(jpaEnderecoEntity.getEstado())
              .pessoaPorId(JpaEnderecoMapper.toDomain(jpaEnderecoEntity.getPessoaPorId()))
              .pessoaPorUuid(JpaEnderecoMapper.toDomain(jpaEnderecoEntity.getPessoaPorUuid()))
              .build();
  }

    public static Pessoa toDomain(JpaPessoaEntity jpaPessoaEntity){
        return Pessoa.builder()
                .id(jpaPessoaEntity.getId())
                .uuid(jpaPessoaEntity.getUuid())
                .nome(jpaPessoaEntity.getNome())
                .cursoPorId(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorId()))
                .cursoPorUuid(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaEntity.getCursoPorUuid()))
                .produtoListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaEntity.getProdutoListComJoinTable()))
                .produtoListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaEntity.getProdutoListComEmbeddable()))
                .pedidoListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComJoinTable()))
                .pedidoListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaEntity.getPedidoListComEmbeddable()))
                .build();
    }
}
