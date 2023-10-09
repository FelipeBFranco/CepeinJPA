package cepein.mapeamento.produto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "produto")
@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;
    private UUID uuid;
    @Size(max = 70, message = "O campo 'descricao' deve ter no máximo {max} caracteres")
    private String descricao;
}