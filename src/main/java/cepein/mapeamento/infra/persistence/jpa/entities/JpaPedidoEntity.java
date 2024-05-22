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
@Table(name = "pedido")
public class JpaPedidoEntity {
    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String uuid;

    @ManyToMany(mappedBy = "pedidoListComJoinTable")
    private List<JpaPessoaEntity> pessoaListComJoinTable;

    @OneToMany(mappedBy = "pedido")
    private List<JpaPessoaPedidoEntity> pessoaListComEmbeddable;

}
