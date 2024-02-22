package cepein.mapeamento.pessoa_pedido;

import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa.model.Pessoa;
import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "uuid_pessoa_fk",referencedColumnName = "uuid")
    private Pessoa pessoa;

    @MapsId("UuidPedido")
    @ManyToOne
    @JoinColumn(name = "uuid_pedido_fk",referencedColumnName = "uuid")
    private Pedido pedido;
}
