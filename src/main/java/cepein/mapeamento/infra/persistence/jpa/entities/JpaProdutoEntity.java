package cepein.mapeamento.infra.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "produto")
public class JpaProdutoEntity {
    @Id
    @Column(name = "id_produto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToMany(mappedBy = "produtoListComJoinTable")
    private List<JpaPessoaEntity> pessoaListComJoinTable;

    @OneToMany(mappedBy = "produto")
    private List<JpaPessoaProdutoEntity> pessoaListComEmbeddable;

}
