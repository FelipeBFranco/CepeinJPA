package cepein.mapeamento.pessoa.forms;

import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa.model.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PessoaForms {


    private String nome;

    private String uuid;

    private List<Pedido> pedidoList;
    public Pessoa converter(Pessoa pessoa){
        pessoa.setNome(this.nome);
        pessoa.setUuid(this.uuid);
        return  pessoa;
    }
}
