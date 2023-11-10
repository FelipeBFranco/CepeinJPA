package cepein.mapeamento.pessoa.model;


import cepein.mapeamento.endereco.model.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
@Entity
@Getter
@Setter
public class Pessoa {

    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    
    @Column(name = "uuid_pessoa")
    @NotNull(message = " campo de {uuid_pessoa} não pode estar nulo/vazio")
    private String uuidPessoa;

    @Column(name = "nome_pessoa")
    @NotNull(message = " campo de {nome_pessoa} não pode estar nulo/vazio")
    private String nomePessoa;
    
    @OneToOne()
    @JoinColumn(name = "id_endereco_fk")
    private Endereco Endereco;

}
