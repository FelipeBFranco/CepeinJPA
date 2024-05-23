package cepein.mapeamento.acore.domain.models.endereco;


import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.acore.domain.validators.Tamanho;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoQuery {

    private Long id_endereco;

    private String uuid;

    @Tamanho(max = "100",min = "5")
    private String rua;

    private String cep;

    @Tamanho(max = "100",min = "5")
    private String cidade;

    private String estado;

    private PessoaQuery pessoaQueryPorId;
    private PessoaQuery pessoaQueryPorUuid;
}
