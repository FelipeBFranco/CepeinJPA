package cepein.mapeamento.infra.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pessoa")
public class JpaPessoaEntity {
    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String uuid;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_endereco_fk", referencedColumnName = "id_endereco")
    private JpaEnderecoEntity enderecoPorId;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "uuid_endereco_fk", referencedColumnName = "uuid")
    private JpaEnderecoEntity enderecoPorUuid;


    @OneToMany(mappedBy = "pessoaPorId", cascade = CascadeType.ALL)
    private List<JpaCursoEntity> cursoPorId;

    @OneToMany(mappedBy = "pessoaPorUuid", cascade = CascadeType.ALL)
    private List<JpaCursoEntity> cursoPorUuid;

    @ManyToMany
    @JoinTable(name="pessoa_produto",
            joinColumns=
            @JoinColumn(name="id_pessoa_fk", referencedColumnName="id_pessoa"),
            inverseJoinColumns=
            @JoinColumn(name="id_produto_fk", referencedColumnName="id_produto"))
    private List<JpaProdutoEntity> produtoListComJoinTable;

    @OneToMany(mappedBy = "pessoa")
    private List<JpaPessoaProdutoEntity> produtoListComEmbeddable;


    @ManyToMany
    @JoinTable(name="pessoa_pedido",
            joinColumns=
            @JoinColumn(name="uuid_pessoa_fk", referencedColumnName="uuid"),
            inverseJoinColumns=
            @JoinColumn(name="uuid_pedido_fk", referencedColumnName="uuid"))
    private List<JpaPedidoEntity> pedidoListComJoinTable;

    @OneToMany(mappedBy = "pessoa")
    private List<JpaPessoaPedidoEntity> pedidoListComEmbeddable;


}
