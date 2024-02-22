package cepein.mapeamento.endereco.model;

import cepein.mapeamento.pessoa.model.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
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
