package cepein.mapeamento.model;

import cepein.mapeamento.model.Pessoa;
import cepein.mapeamento.model.PessoaPedido;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "pedido")
public class Pedido {

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String uuid;

    @ManyToMany(mappedBy = "pedidoListComJoinTable")
    private List<Pessoa> pessoaListComJoinTable;

    @OneToMany(mappedBy = "pedido")
    private List<PessoaPedido> pessoaListComEmbeddable;
}
