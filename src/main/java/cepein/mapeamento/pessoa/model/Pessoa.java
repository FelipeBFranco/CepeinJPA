package cepein.mapeamento.pessoa.model;

import cepein.mapeamento.endereco.model.Endereco;
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
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Pessoa;

    private String nome;

    private String uuid;

    @OneToOne
    @JoinColumn(name = "id_endereco_fk", referencedColumnName = "id_endereco")
    private Endereco enderecoPorId;

   /* @OneToOne
    @JoinColumn(name = "uuid_endereco_fk", referencedColumnName = "uuid")
    private Endereco enderecoPorUuid;*/
    @Column(name = "uuid_endereco_fk")
    private String enderecoPorUuid;

}
