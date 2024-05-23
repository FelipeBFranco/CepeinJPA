package cepein.mapeamento.acore.domain.models.pessoa;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaCommand {

    private Long id;

    private String nome;

    private String uuid;

    private Long idEndereco;
    private String uuidEndereco;
}
