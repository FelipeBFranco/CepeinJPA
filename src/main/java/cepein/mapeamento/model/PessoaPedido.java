package cepein.mapeamento.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PessoaPedido {

    private  static  final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    private PessoaPedidoId pessoaPedidoId;

    @MapsId("uuidPessoa")
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "uuid_pessoa_fk",referencedColumnName = "uuid",insertable=false, updatable=false)
    private Pessoa pessoa;

    @MapsId("uuidPedido")
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "uuid_pedido_fk",referencedColumnName = "uuid",insertable=false, updatable=false)
    private Pedido pedido;
    @PrePersist
    public void setPessoaPedidoId(){
        this.pessoaPedidoId = new PessoaPedidoId();
        this.pessoaPedidoId.setUuidPessoa(this.pessoa.getUuid());
        this.pessoaPedidoId.setUuidPedido(this.pedido.getUuid());
    }

}
