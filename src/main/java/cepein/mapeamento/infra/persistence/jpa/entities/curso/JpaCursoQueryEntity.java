package cepein.mapeamento.infra.persistence.jpa.entities.curso;

import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "curso")
public class JpaCursoQueryEntity {
    @Id
    @Column(name = "id_curso")
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_fk" , referencedColumnName = "id_pessoa")
    private JpaPessoaQueryEntity pessoaPorId;

    @ManyToOne
    @JoinColumn(name = "uuid_pessoa_fk", referencedColumnName = "uuid")
    private JpaPessoaQueryEntity pessoaPorUuid;
}
