package cepein.mapeamento.pessoa.model;


import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.produto.model.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
@Entity
@Getter
@Setter

public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "pessoa")
    private List<Produto> produtos;

}
