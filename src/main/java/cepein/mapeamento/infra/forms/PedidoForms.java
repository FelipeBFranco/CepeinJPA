package cepein.mapeamento.infra.forms;


import cepein.mapeamento.model.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoForms {

    private String descricao;

    private String uuid;

    private List<Long> listaDosIdsDasPessoas;


    public Pedido converter(Pedido pedido){
        pedido.setDescricao(this.descricao);
        pedido.setUuid(this.uuid);
        return pedido;
    }
       //pedido.setUuid(pedidoForms.getUuid() != null ? pedidoForms.getUuid() : pedido.getUuid());
      //  pedido.setDescricao(pedidoForms.getDescricao() != null ? pedidoForms.getDescricao() : pedido.getDescricao());



}
