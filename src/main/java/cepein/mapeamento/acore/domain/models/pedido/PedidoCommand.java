package cepein.mapeamento.acore.domain.models.pedido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoCommand {
    private Long id;

    private String descricao;

    private String uuid;
}
