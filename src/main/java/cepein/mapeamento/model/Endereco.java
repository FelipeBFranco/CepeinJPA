package cepein.mapeamento.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @EqualsAndHashCode.Include//conparação mais superficial
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_endereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

    @OneToOne(mappedBy = "enderecoPorId")
    private Pessoa pessoaPorId;

    @OneToOne(mappedBy = "enderecoPorUuid")
    private Pessoa pessoaPorUuid;
}
