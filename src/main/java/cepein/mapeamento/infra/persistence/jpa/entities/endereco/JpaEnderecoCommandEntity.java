package cepein.mapeamento.infra.persistence.jpa.entities.endereco;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class JpaEnderecoCommandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_endereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

}
