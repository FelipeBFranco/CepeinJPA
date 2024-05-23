package cepein.mapeamento.acore.domain.models.pedido;


import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoQuery {

    private Long id;

    private String descricao;

    private String uuid;

    private List<PessoaQuery> pessoaQueryListComJoinTable;
    private List<PessoaQuery> pessoaQueryListComEmbeddable;
}