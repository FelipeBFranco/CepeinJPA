package cepein.mapeamento.infra.persistence.jpa.mapper;


import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.models.curso.CursoCommand;
import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.infra.persistence.jpa.entities.curso.JpaCursoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.curso.JpaCursoQueryEntity;

public class JpaCursoMapper {

    public static JpaCursoCommandEntity toEntity(CursoCommand cursoDomain){
        return JpaCursoCommandEntity.builder()
                .id(cursoDomain.getId())
                .descricao(cursoDomain.getDescricao())
                .build();
    }
    public static CursoQuery toDomain(JpaCursoQueryEntity jpaCursoEntity){
        return CursoQuery.builder()
                .id(jpaCursoEntity.getId())
                .descricao(jpaCursoEntity.getDescricao())
                .pessoaQueryPorId(JpaCursoMapper.toDomain(jpaCursoEntity.getPessoaPorId()))
                .pessoaQueryPorUuid(JpaCursoMapper.toDomain(jpaCursoEntity.getPessoaPorUuid()))
                .build();
    }
    public static PessoaQuery toDomain(JpaPessoaQueryEntity jpaPessoaQueryEntity){
        return PessoaQuery.builder()
                .id(jpaPessoaQueryEntity.getId())
                .uuid(jpaPessoaQueryEntity.getUuid())
                .nome(jpaPessoaQueryEntity.getNome())
                .enderecoQueryPorId(JpaPessoaMapper.toDomain(jpaPessoaQueryEntity.getEnderecoPorId()))
                .enderecoQueryPorUuid(JpaPessoaMapper.toDomain(jpaPessoaQueryEntity.getEnderecoPorUuid()))
                .produtoQueryListComJoinTable(JpaPessoaMapper.produtoDePessoatoDomain(jpaPessoaQueryEntity.getProdutoListComJoinTable()))
                .produtoQueryListComEmbeddable(JpaPessoaMapper.pessoaProdutoDePessoaToDomain(jpaPessoaQueryEntity.getProdutoListComEmbeddable()))
                .pedidoQueryListComJoinTable(JpaPessoaMapper.pedidoDePessoaToDomain(jpaPessoaQueryEntity.getPedidoListComJoinTable()))
                .pedidoQueryListComEmbeddable(JpaPessoaMapper.pessoaPedidoDePessoaToDomain(jpaPessoaQueryEntity.getPedidoListComEmbeddable()))
                .build();
    }


}
