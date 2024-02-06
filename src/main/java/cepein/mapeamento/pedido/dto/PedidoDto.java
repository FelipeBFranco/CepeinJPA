package cepein.mapeamento.pedido.dto;

import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PedidoDto {

    private Long id;

    private String descricao;

    private String uuid;

    private List<PessoaDtoParaRelacionamento> pessoaListComJoinTable;

    public static List<PedidoDto> converter(List<Pedido> pedidoList){
        return pedidoList.stream()
                .map(pedido -> new PedidoDto(pedido.getId(), pedido.getDescricao(), pedido.getUuid(),PessoaDtoParaRelacionamento.convet(pedido.getPessoaListComJoinTable())))
                .collect(Collectors.toList());
    }
}