package cepein.mapeamento.acore.domain.models.pessoa;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaProdutoCommand {
    private Long idPessoa;
    private Long idProduto;
}
