package cepein.mapeamento.infra.persistence.jpa.entities.pessoa;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pessoa")
public class JpaPessoaCommandEntity {
    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String uuid;

    @Column(name = "id_endereco_fk")
    private Long idEndereco;


    @Column(name = "uuid_endereco_fk")
    private String uuidEndereco;




}
