package cepein.mapeamento.acore.domain.models.produto;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoQuery {

    private Long id;

    private String descricao;

    private List<PessoaQuery> pessoaQueryListComJoinTable;
    private List<PessoaQuery> pessoaQueryListComEmbeddable;
}
