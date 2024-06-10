package cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido;


import cepein.mapeamento.infra.persistence.jpa.entities.pedido.JpaPedidoQueryEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.JpaPessoaQueryEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JpaPessoaPedidoQueryEntity {
    private  static  final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    private JpaPessoaPedidoIdEntity pessoaPedidoId;

    @MapsId("uuidPessoa")
    @ManyToOne
    @JoinColumn(name = "uuid_pessoa_fk",referencedColumnName = "uuid",insertable=false, updatable=false)
    private JpaPessoaQueryEntity pessoa;

    @MapsId("uuidPedido")
    @ManyToOne
    @JoinColumn(name = "uuid_pedido_fk",referencedColumnName = "uuid",insertable=false, updatable=false)
    private JpaPedidoQueryEntity pedido;

    public JpaPessoaPedidoQueryEntity(JpaPessoaQueryEntity pessoa, JpaPedidoQueryEntity pedido) {
        this.pedido = pedido;
        this.pessoa = pessoa;
    }

    @PrePersist
    public void setPessoaPedidoId(){
        this.pessoaPedidoId = new JpaPessoaPedidoIdEntity();
        this.pessoaPedidoId.setUuidPessoa(this.pessoa.getUuid());
        this.pessoaPedidoId.setUuidPedido(this.pedido.getUuid());
    }
}
