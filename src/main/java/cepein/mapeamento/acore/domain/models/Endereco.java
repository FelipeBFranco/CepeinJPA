package cepein.mapeamento.acore.domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    private Long id_endereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

    private Pessoa pessoaPorId;
    private Pessoa pessoaPorUuid;
}
