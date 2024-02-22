package cepein.mapeamento.produto.model;

import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa_produto.PessoaProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "id_produto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToMany(mappedBy = "produtoListComJoinTable")
    private List<Pessoa> pessoaListComJoinTable;

    @OneToMany(mappedBy = "produto")
    private List<PessoaProduto> pessoaListComEmbeddable;
}
