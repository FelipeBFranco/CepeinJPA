package cepein.mapeamento.pessoa.model;

import cepein.mapeamento.curso.model.Curso;
import cepein.mapeamento.endereco.model.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pessoa;

    private String nome;

    private String uuid;

    @OneToOne
    @JoinColumn(name = "id_endereco_fk", referencedColumnName = "idEndereco")
    private Endereco enderecoPorId;

    @OneToOne
    @JoinColumn(name = "uuid_endereco_fk", referencedColumnName = "uuid")
    private Endereco enderecoPorUuid;

    @OneToMany(mappedBy = "pessoaPorId")
    private List<Curso> cursoPorId;

    @OneToMany(mappedBy = "pessoaPorUuid")
    private List<Curso> cursoPorUuid;
}
