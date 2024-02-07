package cepein.mapeamento.pedido.forms;


import cepein.mapeamento.pedido.model.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoForms {

    private String descricao;

    private String uuid;

    public Pedido converter(Pedido pedido){
        pedido.setDescricao(this.getDescricao());
        pedido.setUuid(this.uuid);
        return pedido;
    }



}
