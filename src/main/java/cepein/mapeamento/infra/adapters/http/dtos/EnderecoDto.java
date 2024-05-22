package cepein.mapeamento.infra.adapters.http.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class EnderecoDto {

    private Long idEndereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

    private PessoaDto pessoaPorId;
    private PessoaDto pessoaPorUuid;
}
