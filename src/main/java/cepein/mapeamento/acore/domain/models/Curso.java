package cepein.mapeamento.acore.domain.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    private Long id;

    private String descricao;

    private Pessoa pessoaPorId;

    private Pessoa pessoaPorUuid;
}
