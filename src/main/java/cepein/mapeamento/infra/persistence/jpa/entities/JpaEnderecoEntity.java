package cepein.mapeamento.infra.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.*;
@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class JpaEnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_endereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

    @OneToOne(mappedBy = "enderecoPorId")
    private JpaPessoaEntity pessoaPorId;

    @OneToOne(mappedBy = "enderecoPorUuid")
    private JpaPessoaEntity pessoaPorUuid;


}
