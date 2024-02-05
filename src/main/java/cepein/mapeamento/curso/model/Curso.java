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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_curso_fk" , referencedColumnName = "id_Pessoa")
    private Pessoa pessoaPorId;

    @ManyToOne
    @JoinColumn(name = "uuid_curso_fk", referencedColumnName = "uuid")
    private Pessoa pessoaPorUuid;
}
