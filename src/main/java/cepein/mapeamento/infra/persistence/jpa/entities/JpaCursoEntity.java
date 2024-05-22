package cepein.mapeamento.infra.persistence.jpa.entities;

import cepein.mapeamento.acore.domain.models.Pessoa;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "curso")
public class JpaCursoEntity {

    @Id
    @Column(name = "id_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_fk" , referencedColumnName = "id_pessoa")
    private JpaPessoaEntity pessoaPorId;

    @ManyToOne
    @JoinColumn(name = "uuid_pessoa_fk", referencedColumnName = "uuid")
    private JpaPessoaEntity pessoaPorUuid;
}
