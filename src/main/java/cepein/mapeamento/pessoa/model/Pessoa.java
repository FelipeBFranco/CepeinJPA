package cepein.mapeamento.pessoa.model;


import cepein.mapeamento.endereco.model.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
@Entity
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    private UUID uuid;

    @Size(max = 70, message = "O campo 'nome' deve ter no máximo {max} caracteres!")
    private String nome;

    private Endereco endereco;

    private String uuidEndereco;
}
