package cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto;


import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoIdEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.produto.JpaProdutoQueryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_produto")
public class JpaPessoaProdutoQueryEntity {

    @EmbeddedId
    private JpaPessoaProdutoIdEntity pessoaProdutoId;

    @MapsId("idPessoa")
    @ManyToOne
    @JoinColumn(name = "id_pessoa_fk")
    private JpaPessoaQueryEntity pessoa;

    @MapsId("idProduto")
    @ManyToOne
    @JoinColumn(name = "id_produto_fk")
    private JpaProdutoQueryEntity produto;

}
