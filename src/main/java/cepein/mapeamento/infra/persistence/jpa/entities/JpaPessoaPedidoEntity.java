package cepein.mapeamento.infra.persistence.jpa.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa_pedido")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JpaPessoaPedidoEntity {
    private  static  final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    private JpaPessoaPedidoIdEntity pessoaPedidoId;

    @MapsId("uuidPessoa")
    @ManyToOne
    @JoinColumn(name = "uuid_pessoa_fk",referencedColumnName = "uuid",insertable=false, updatable=false)
    private JpaPessoaEntity pessoa;

    @MapsId("uuidPedido")
    @ManyToOne
    @JoinColumn(name = "uuid_pedido_fk",referencedColumnName = "uuid",insertable=false, updatable=false)
    private JpaPedidoEntity pedido;

    public JpaPessoaPedidoEntity(JpaPessoaEntity pessoa, JpaPedidoEntity pedido) {
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
