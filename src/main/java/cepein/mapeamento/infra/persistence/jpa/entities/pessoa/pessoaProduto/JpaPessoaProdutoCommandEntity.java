package cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class JpaPessoaProdutoCommandEntity {
    @EmbeddedId
    private JpaPessoaProdutoIdEntity pessoaProdutoId;
}
