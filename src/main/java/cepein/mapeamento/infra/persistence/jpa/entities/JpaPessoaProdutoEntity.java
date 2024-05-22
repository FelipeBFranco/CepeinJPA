package cepein.mapeamento.infra.persistence.jpa.entities;


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
public class JpaPessoaProdutoEntity {

    @EmbeddedId
    private JpaPessoaProdutoIdEntity pessoaProdutoId;

    @MapsId("idPessoa")
    @ManyToOne
    @JoinColumn(name = "id_pessoa_fk")
    private JpaPessoaEntity pessoa;

    @MapsId("idProduto")
    @ManyToOne
    @JoinColumn(name = "id_produto_fk")
    private JpaProdutoEntity produto;

}
