package cepein.mapeamento.infra.adapters.http.forms;


import cepein.mapeamento.acore.domain.models.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoForms {

    private Long id;

    private String descricao;

    private String uuid;

    private List<Long> listaDosIdsDasPessoas;


    public Pedido converter(Pedido pedido){
        pedido.setDescricao(this.getDescricao() != null ? this.getDescricao() : pedido.getDescricao());
        pedido.setUuid(this.getUuid() != null ? this.getUuid() : pedido.getUuid());
        return pedido;
    }
    public Pedido converter(){
        Pedido pedido = new Pedido();
        pedido.setDescricao(this.descricao);
        pedido.setUuid(this.uuid);
        return pedido;
    }

}
