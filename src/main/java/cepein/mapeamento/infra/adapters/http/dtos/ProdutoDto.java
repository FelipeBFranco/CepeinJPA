package cepein.mapeamento.infra.adapters.http.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ProdutoDto {

    private Long id;

    private String descricao;

    private List<PessoaDto> pessoaListComJoinTable;
    private List<PessoaDto> pessoaListComEmbeddable;
}
