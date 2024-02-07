package cepein.mapeamento.pedido.forms;

import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa_pedido.PessoaPedido;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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



}
