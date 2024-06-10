package cepein.mapeamento.acore.domain.models.curso;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoCommand {
    private Long id;

    private String descricao;

    private Long idPessoa;

    private String uuidPessoa;
}

