package cepein.mapeamento.endereco.model;

import cepein.mapeamento.pessoa.model.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "id_endereco")
    private Long idEndereco;

    private String uuid;

    @OneToOne(mappedBy = "enderecoPorId")
    private Pessoa pessoaPorId;

    @OneToOne(mappedBy = "enderecoPorUuid")
    private Pessoa pessoaPorUuid;


}
