package cepein.mapeamento.infra.adapters.http.dtos;



import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class PessoaDto  {

    private Long id;

    private String nome;

    private String uuid;

    private EnderecoDto enderecoPorId;

    private EnderecoDto enderecoPorUuid;

    private List<CursoDto> cursoListPorId;
    private List<CursoDto> cursoListPorUuid;

    private List<ProdutoDto> produtoListComJoinTable;
    private List<ProdutoDto> produtoListComEmbeddable;

    private List<PedidoDto> pedidoListComJoinTable;
    private List<PedidoDto> pedidoListComEmbeddable;
}
