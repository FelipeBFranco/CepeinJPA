package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.acore.domain.models.Pedido;
import cepein.mapeamento.infra.adapters.http.dtos.PedidoDto;


import java.util.Objects;

public class PedidoViewModel {
    public static PedidoDto toDto(Pedido pedido){
        return PedidoDto.builder()
                        .id(pedido.getId())
                        .uuid(pedido.getUuid())
                        .descricao(pedido.getDescricao())

                        .pessoaListComJoinTable(Objects.isNull(pedido.getPessoaListComJoinTable())?
                                null : PessoaViewModel.toDto(pedido.getPessoaListComJoinTable()))
                        .pessoaListComEmbeddable(Objects.isNull(pedido.getPessoaListComEmbeddable())?
                                null : PessoaViewModel.toDto(pedido.getPessoaListComEmbeddable()))
                        .build();
    }

}
