package cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JpaPessoaPedidoIdEntity implements Serializable {

    @Getter(value = AccessLevel.NONE)
    private  static  final long serialVersionUID = 1L;

    @Column(name = "uuid_pessoa_fk")
    @EqualsAndHashCode.Include
    private String uuidPessoa;

    @Column(name = "uuid_pedido_fk")
    @EqualsAndHashCode.Include
    private String uuidPedido;
}
