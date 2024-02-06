package cepein.mapeamento.pessoa_produto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PessoaProduto implements  Serializable{

    @Id
    @Column(name = "id_pessoa_fk")
    private Long idPessoa;

    @Id
    @Column(name = "id_produto_fk")
    private Long idProduto;
}
