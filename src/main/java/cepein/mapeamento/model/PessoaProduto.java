package cepein.mapeamento.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_produto")
public class PessoaProduto {

    @EmbeddedId
    private PessoaProdutoId pessoaProdutoId;

    @MapsId("idPessoa")
    @ManyToOne
    @JoinColumn(name = "id_pessoa_fk")
    private Pessoa pessoa;

    @MapsId("idProduto")
    @ManyToOne
    @JoinColumn(name = "id_produto_fk")
    private Produto produto;
}
