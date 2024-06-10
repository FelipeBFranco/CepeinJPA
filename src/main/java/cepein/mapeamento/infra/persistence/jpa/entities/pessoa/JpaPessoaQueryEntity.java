package cepein.mapeamento.infra.persistence.jpa.entities.pessoa;

import cepein.mapeamento.infra.persistence.jpa.entities.curso.JpaCursoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.endereco.JpaEnderecoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pedido.JpaPedidoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.produto.JpaProdutoQueryEntity;
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
public class JpaPessoaQueryEntity {

    @Id
    @Column(name = "id_pessoa")
    private Long id;

    private String nome;

    private String uuid;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_endereco_fk", referencedColumnName = "id_endereco")
    private JpaEnderecoQueryEntity enderecoPorId;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "uuid_endereco_fk", referencedColumnName = "uuid")
    private JpaEnderecoQueryEntity enderecoPorUuid;


    @OneToMany(mappedBy = "pessoaPorId", cascade = CascadeType.ALL)
    private List<JpaCursoQueryEntity> cursoPorId;

    @OneToMany(mappedBy = "pessoaPorUuid", cascade = CascadeType.ALL)
    private List<JpaCursoQueryEntity> cursoPorUuid;

    @ManyToMany
    @JoinTable(name="pessoa_produto",
            joinColumns=
            @JoinColumn(name="id_pessoa_fk", referencedColumnName="id_pessoa"),
            inverseJoinColumns=
            @JoinColumn(name="id_produto_fk", referencedColumnName="id_produto"))
    private List<JpaProdutoQueryEntity> produtoListComJoinTable;

    @OneToMany(mappedBy = "pessoa")
    private List<JpaPessoaProdutoQueryEntity> produtoListComEmbeddable;


    @ManyToMany
    @JoinTable(name="pessoa_pedido",
            joinColumns=
            @JoinColumn(name="uuid_pessoa_fk", referencedColumnName="uuid"),
            inverseJoinColumns=
            @JoinColumn(name="uuid_pedido_fk", referencedColumnName="uuid"))
    private List<JpaPedidoQueryEntity> pedidoListComJoinTable;

    @OneToMany(mappedBy = "pessoa")
    private List<JpaPessoaPedidoQueryEntity> pedidoListComEmbeddable;


}
