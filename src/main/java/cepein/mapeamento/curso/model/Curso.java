package cepein.mapeamento.curso.model;

import cepein.mapeamento.pessoa.model.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @Column(name = "id_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_fk" , referencedColumnName = "id_pessoa")
    private Pessoa pessoaPorId;

    @ManyToOne
    @JoinColumn(name = "uuid_pessoa_fk", referencedColumnName = "uuid")
    private Pessoa pessoaPorUuid;
}
