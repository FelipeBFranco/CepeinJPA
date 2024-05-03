package cepein.mapeamento.infra.adapters.http.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class CursoDto {
    private Long id;

    private String descricao;

    private PessoaDto pessoaPorId;

    private PessoaDto pessoaPorUuid;

}
