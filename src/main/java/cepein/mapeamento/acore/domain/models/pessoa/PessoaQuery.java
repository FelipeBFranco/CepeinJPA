package cepein.mapeamento.acore.domain.models.pessoa;


import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PessoaQuery {


    private Long id;

    private String nome;

    private String uuid;

    private EnderecoQuery enderecoQueryPorId;
    private EnderecoQuery enderecoQueryPorUuid;


    private List<CursoQuery> cursoPorId;
    private List<CursoQuery> cursoPorUuid;


    private List<ProdutoQuery> produtoQueryListComJoinTable;
    private List<ProdutoQuery> produtoQueryListComEmbeddable;


    private List<PedidoQuery> pedidoQueryListComJoinTable;
    private List<PedidoQuery> pedidoQueryListComEmbeddable;


}

