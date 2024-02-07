package cepein.mapeamento.pessoa_pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PessoaPedidoId implements Serializable {

    @Column(name = "uuid_pessoa_fk")
    private String UuidPessoa;

    @Column(name = "uuid_pedido_fk")
    private String UuidPedido;
}
