package cepein.mapeamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String uuid;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_endereco_fk", referencedColumnName = "id_endereco")
    private Endereco enderecoPorId;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "uuid_endereco_fk", referencedColumnName = "uuid")
    private Endereco enderecoPorUuid;


    @OneToMany(mappedBy = "pessoaPorId", cascade = CascadeType.ALL)
    private List<Curso> cursoPorId;

    @OneToMany(mappedBy = "pessoaPorUuid", cascade = CascadeType.ALL)
    private List<Curso> cursoPorUuid;

    @ManyToMany
    @JoinTable(name="pessoa_produto",
            joinColumns=
            @JoinColumn(name="id_pessoa_fk", referencedColumnName="id_pessoa"),
            inverseJoinColumns=
            @JoinColumn(name="id_produto_fk", referencedColumnName="id_produto"))
    private List<Produto> produtoListComJoinTable;

    @OneToMany(mappedBy = "pessoa")
    private List<PessoaProduto> produtoListComEmbeddable;


    @ManyToMany
    @JoinTable(name="pessoa_pedido",
            joinColumns=
            @JoinColumn(name="uuid_pessoa_fk", referencedColumnName="uuid"),
            inverseJoinColumns=
            @JoinColumn(name="uuid_pedido_fk", referencedColumnName="uuid"))
    private List<Pedido> pedidoListComJoinTable;

    @OneToMany(mappedBy = "pessoa")
    private List<PessoaPedido> pedidoListComEmbeddable;


}
