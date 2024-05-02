package cepein.mapeamento.infra.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JpaPessoaProdutoIdEntity implements Serializable {
    @Column(name = "id_pessoa_fk")
    private Long idPessoa;


    @Column(name = "id_produto_fk")
    private Long idProduto;
}
