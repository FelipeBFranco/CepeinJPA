package cepein.mapeamento.infra.persistence.jpa.entities.pedido;


import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoQueryEntity;
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
public class JpaPedidoQueryEntity {
    @Id
    @Column(name = "id_pedido")
    private Long id;

    private String descricao;

    private String uuid;

    @ManyToMany(mappedBy = "pedidoListComJoinTable")
    private List<JpaPessoaQueryEntity> pessoaListComJoinTable;

    @OneToMany(mappedBy = "pedido")
    private List<JpaPessoaPedidoQueryEntity> pessoaListComEmbeddable;

}
