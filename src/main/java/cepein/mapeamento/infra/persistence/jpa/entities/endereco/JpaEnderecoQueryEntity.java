package cepein.mapeamento.infra.persistence.jpa.entities.endereco;

import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import jakarta.persistence.*;
import lombok.*;
@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class JpaEnderecoQueryEntity {

    @Id
    private Long id_endereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

    @OneToOne(mappedBy = "enderecoPorId")
    private JpaPessoaQueryEntity pessoaPorId;

    @OneToOne(mappedBy = "enderecoPorUuid")
    private JpaPessoaQueryEntity pessoaPorUuid;


}
