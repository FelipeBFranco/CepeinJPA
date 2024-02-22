package cepein.mapeamento.pedido.forms;


import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa.model.Pessoa;
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



}
