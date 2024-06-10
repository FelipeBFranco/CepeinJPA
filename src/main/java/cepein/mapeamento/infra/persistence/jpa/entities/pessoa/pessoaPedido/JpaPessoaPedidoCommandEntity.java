package cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_pedido")
public class JpaPessoaPedidoCommandEntity {

    @EmbeddedId
    private JpaPessoaPedidoIdEntity pessoaPedidoId;

}
