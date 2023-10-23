package cepein.mapeamento.pedido.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedido")
@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    private UUID uuid;
    @Size(max = 70, message = "O campo 'descricao' deve ter no máximo {max} caracteres")
    private String descricao;

}
