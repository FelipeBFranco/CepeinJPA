package cepein.mapeamento.infra.adapters.http.forms;


import cepein.mapeamento.acore.domain.models.pedido.PedidoCommand;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoForms {

    private Long id;
    private String uuid;
    private String descricao;


    public PedidoCommand converter(PedidoQuery pedidoQuery){

        PedidoCommand pedidoCommand = new PedidoCommand();
        pedidoCommand.setDescricao(this.getDescricao() != null ? this.getDescricao() : pedidoQuery.getDescricao());
        pedidoCommand.setUuid(this.getUuid() != null ? this.getUuid() : pedidoQuery.getUuid());
        return pedidoCommand;
    }
    public PedidoCommand converter(){

        PedidoCommand pedidoCommand = new PedidoCommand();
        pedidoCommand.setDescricao(this.descricao);
        pedidoCommand.setUuid(this.uuid);
        return pedidoCommand;
    }

}
