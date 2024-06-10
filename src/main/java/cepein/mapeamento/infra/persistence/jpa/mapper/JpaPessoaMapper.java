package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaCommand;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.infra.persistence.jpa.entities.curso.JpaCursoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.endereco.JpaEnderecoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pedido.JpaPedidoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.produto.JpaProdutoQueryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class JpaPessoaMapper {
    public static JpaPessoaCommandEntity toEntity(PessoaCommand pessoaCommand){
        return JpaPessoaCommandEntity.builder()
                .id(pessoaCommand.getId())
                .uuid(pessoaCommand.getUuid())
                .nome(pessoaCommand.getNome())
                .idEndereco(pessoaCommand.getIdEndereco())
                .uuidEndereco(pessoaCommand.getUuid())
                .build();
    }
    public static PessoaQuery toDomain(JpaPessoaQueryEntity jpaPessoaQueryEntity){
        return PessoaQuery.builder()
                .id(jpaPessoaQueryEntity.getId())
                .uuid(jpaPessoaQueryEntity.getUuid())
                .nome(jpaPessoaQueryEntity.getNome())
                .enderecoQueryPorId(JpaPessoaMapper.toDomain(jpaPessoaQueryEntity.getEnderecoPorId()))
                .enderecoQueryPorUuid(JpaPessoaMapper.toDomain(jpaPessoaQueryEntity.getEnderecoPorUuid()))
                .cursoPorId(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaQueryEntity.getCursoPorId()))
                .cursoPorUuid(JpaPessoaMapper.cursoDePessoaToDomain(jpaPessoaQueryEntity.getCursoPorUuid()))
                .produtoQueryListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaQueryEntity.getProdutoListComJoinTable()))
                .produtoQueryListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaQueryEntity.getProdutoListComEmbeddable()))
                .pedidoQueryListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaQueryEntity.getPedidoListComJoinTable()))
                .pedidoQueryListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaQueryEntity.getPedidoListComEmbeddable()))
                .build();
    }

    public  static EnderecoQuery toDomain(JpaEnderecoQueryEntity jpaEnderecoQueryEntity){
        return EnderecoQuery.builder()
                .id_endereco(jpaEnderecoQueryEntity.getId_endereco())
                .uuid(jpaEnderecoQueryEntity.getUuid())
                .cep(jpaEnderecoQueryEntity.getCep())
                .cidade(jpaEnderecoQueryEntity.getCidade())
                .estado(jpaEnderecoQueryEntity.getEstado())
                .build();
    }
    public static List<CursoQuery> cursoDePessoaToDomain(List<JpaCursoQueryEntity> jpaCursoEntityList){
        return jpaCursoEntityList
                .stream()
                .map(curso->CursoQuery.builder()
                        .id(curso.getId())
                        .descricao(curso.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<ProdutoQuery> produtoDePessoatoDomain(List<JpaProdutoQueryEntity> jpaProdutoQueryEntityList){
        return jpaProdutoQueryEntityList
                .stream()
                .map(jpaProdutoEntity -> ProdutoQuery.builder()
                        .id(jpaProdutoEntity.getId())
                        .descricao(jpaProdutoEntity.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }
    public static List<ProdutoQuery> pessoaProdutoDePessoaToDomain(List<JpaPessoaProdutoQueryEntity> jpaPessoaProdutoEntityQueryEntityList){
        List<JpaProdutoQueryEntity> jpaProdutoQueryEntityList = jpaPessoaProdutoEntityQueryEntityList
                .stream()
                .map(JpaPessoaProdutoQueryEntity::getProduto)
                .collect(Collectors.toList());
        return JpaPessoaMapper.produtoDePessoatoDomain(jpaProdutoQueryEntityList);
    }
    public static List<PedidoQuery> pedidoDePessoaToDomain(List<JpaPedidoQueryEntity> jpaPedidoQueryEntityList){
        return jpaPedidoQueryEntityList.stream()
                .map(jpaPedidoEntity ->  PedidoQuery.builder()
                        .id(jpaPedidoEntity.getId())
                        .uuid(jpaPedidoEntity.getUuid())
                        .descricao(jpaPedidoEntity.getDescricao())
                        .build())
                .collect(Collectors.toList());
    }
    public static List<PedidoQuery> pessoaPedidoDePessoaToDomain(List<JpaPessoaPedidoQueryEntity> jpaPessoaPedidoQueryEntityList){
        List<JpaPedidoQueryEntity> jpaPedidoQueryEntityList = jpaPessoaPedidoQueryEntityList
                .stream()
                .map(JpaPessoaPedidoQueryEntity::getPedido)
                .collect(Collectors.toList());
        return JpaPessoaMapper.pedidoDePessoaToDomain(jpaPedidoQueryEntityList);
    }
}
