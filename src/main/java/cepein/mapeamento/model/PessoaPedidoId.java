package cepein.mapeamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PessoaPedidoId implements Serializable {

    @Getter(value = AccessLevel.NONE)
    private  static  final long serialVersionUID = 1L;

    @Column(name = "uuid_pessoa_fk")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuidPessoa;

    @Column(name = "uuid_pedido_fk")
    @EqualsAndHashCode.Include
    private String uuidPedido;
}
