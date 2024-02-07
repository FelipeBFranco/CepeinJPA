package cepein.mapeamento.pessoa_pedido;

import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa.model.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_pedido")
public class PessoaPedido {

    @EmbeddedId
    private PessoaPedidoId pessoaPedidoId;

    @MapsId("UuidPessoa")
    @ManyToOne
    @JoinColumn(name = "uuid_pessoa_fk")
    private Pessoa pessoa;

    @MapsId("UuidPedido")
    @ManyToOne
    @JoinColumn(name = "uuid_pedido_fk")
    private Pedido pedido;
}
