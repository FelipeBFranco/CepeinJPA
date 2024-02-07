package cepein.mapeamento.pessoa_produto;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PessoaProdutoId  implements Serializable {


    @Column(name = "id_pessoa_fk")
    private Long idPessoa;


    @Column(name = "id_produto_fk")
    private Long idProduto;

}
