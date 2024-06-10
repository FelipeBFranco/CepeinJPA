package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.infra.adapters.http.dtos.PedidoDto;


import java.util.Objects;

public class PedidoViewModel {
    public static PedidoDto toDto(PedidoQuery pedidoQuery){
        return PedidoDto.builder()
                        .id(pedidoQuery.getId())
                        .uuid(pedidoQuery.getUuid())
                        .descricao(pedidoQuery.getDescricao())

                        .pessoaListComJoinTable(Objects.isNull(pedidoQuery.getPessoaQueryListComJoinTable())?
                                null : PessoaViewModel.toDto(pedidoQuery.getPessoaQueryListComJoinTable()))
                        .pessoaListComEmbeddable(Objects.isNull(pedidoQuery.getPessoaQueryListComEmbeddable())?
                                null : PessoaViewModel.toDto(pedidoQuery.getPessoaQueryListComEmbeddable()))
                        .build();
    }

}
