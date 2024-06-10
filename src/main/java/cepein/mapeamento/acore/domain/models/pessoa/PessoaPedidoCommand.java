package cepein.mapeamento.acore.domain.models.pessoa;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaPedidoCommand {
    private String uuidPessoa;
    private String uuidPedido;
}
