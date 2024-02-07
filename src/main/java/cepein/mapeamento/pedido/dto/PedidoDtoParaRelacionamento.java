package cepein.mapeamento.pedido.dto;

import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa_pedido.PessoaPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PedidoDtoParaRelacionamento {

    private Long id;

    private String descricao;

    private String uuid;

    public static List<PedidoDtoParaRelacionamento> converter(List<Pedido> pedidoList){
        return pedidoList.stream()
                .map(pedido -> new PedidoDtoParaRelacionamento(pedido.getId(),
                        pedido.getDescricao(),
                        pedido.getUuid()))
                .collect(Collectors.toList());
    }
    public static List<PedidoDtoParaRelacionamento> converterPessoaPedido(List<PessoaPedido> pessoaPedidoList){
        return pessoaPedidoList.stream()
                .map(pessoaPedido -> new PedidoDtoParaRelacionamento(pessoaPedido.getPedido().getId(),
                        pessoaPedido.getPedido().getDescricao(),
                        pessoaPedido.getPedido().getUuid()))
                .collect(Collectors.toList());
    }
}
