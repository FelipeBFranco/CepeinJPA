package cepein.mapeamento.infra.adapters.http.dtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PedidoDto {

    private Long id;

    private String descricao;

    private String uuid;

    private List<PessoaDto> pessoaListComJoinTable;
    private List<PessoaDto> pessoaListComEmbeddable;
}
