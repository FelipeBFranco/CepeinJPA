package cepein.mapeamento.acore.domain.models.produto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoCommand {

    private Long id;

    private String descricao;
}
