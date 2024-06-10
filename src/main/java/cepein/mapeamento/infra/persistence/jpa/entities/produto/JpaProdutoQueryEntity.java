package cepein.mapeamento.infra.persistence.jpa.entities.produto;

import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoQueryEntity;
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
public class JpaProdutoQueryEntity {
    @Id
    @Column(name = "id_produto")
    private Long id;

    private String descricao;

    @ManyToMany(mappedBy = "produtoListComJoinTable")
    private List<JpaPessoaQueryEntity> pessoaListComJoinTable;

    @OneToMany(mappedBy = "produto")
    private List<JpaPessoaProdutoQueryEntity> pessoaListComEmbeddable;

}
