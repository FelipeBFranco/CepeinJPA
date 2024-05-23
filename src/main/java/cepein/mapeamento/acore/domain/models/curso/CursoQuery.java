package cepein.mapeamento.acore.domain.models.curso;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoQuery {
    private Long id;

    private String descricao;

    private PessoaQuery pessoaQueryPorId;

    private PessoaQuery pessoaQueryPorUuid;
}

