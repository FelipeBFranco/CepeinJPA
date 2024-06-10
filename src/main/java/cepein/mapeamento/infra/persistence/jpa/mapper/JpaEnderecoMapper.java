package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoCommand;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.infra.persistence.jpa.entities.endereco.JpaEnderecoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.endereco.JpaEnderecoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;

public class JpaEnderecoMapper {

    public static JpaEnderecoCommandEntity toEntity(EnderecoCommand enderecoDomain){
        return JpaEnderecoCommandEntity.builder()
                .id_endereco(enderecoDomain.getId_endereco())
                .uuid(enderecoDomain.getUuid())
                .rua(enderecoDomain.getRua())
                .cep(enderecoDomain.getCep())
                .cidade(enderecoDomain.getCidade())
                .estado(enderecoDomain.getEstado())
                .build();
    }
  public  static EnderecoQuery toDomain(JpaEnderecoQueryEntity jpaEnderecoQueryEntity){
      return EnderecoQuery.builder()
              .id_endereco(jpaEnderecoQueryEntity.getId_endereco())
              .uuid(jpaEnderecoQueryEntity.getUuid())
              .rua(jpaEnderecoQueryEntity.getRua())
              .cep(jpaEnderecoQueryEntity.getCep())
              .cidade(jpaEnderecoQueryEntity.getCidade())
              .estado(jpaEnderecoQueryEntity.getEstado())
              .pessoaQueryPorId(JpaEnderecoMapper.toDomain(jpaEnderecoQueryEntity.getPessoaPorId()))
              .pessoaQueryPorUuid(JpaEnderecoMapper.toDomain(jpaEnderecoQueryEntity.getPessoaPorUuid()))
              .build();
  }

    public static PessoaQuery toDomain(JpaPessoaQueryEntity jpaPessoaQueryEntity){
        return PessoaQuery.builder()
                .id(jpaPessoaQueryEntity.getId())
                .uuid(jpaPessoaQueryEntity.getUuid())
                .nome(jpaPessoaQueryEntity.getNome())
                .cursoPorId(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaQueryEntity.getCursoPorId()))
                .cursoPorUuid(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaQueryEntity.getCursoPorUuid()))
                .produtoQueryListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaQueryEntity.getProdutoListComJoinTable()))
                .produtoQueryListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaQueryEntity.getProdutoListComEmbeddable()))
                .pedidoQueryListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaQueryEntity.getPedidoListComJoinTable()))
                .pedidoQueryListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaQueryEntity.getPedidoListComEmbeddable()))
                .build();
    }
}
