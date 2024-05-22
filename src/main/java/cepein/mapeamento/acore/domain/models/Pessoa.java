package cepein.mapeamento.acore.domain.models;


import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pessoa   {


    private Long id;

    private String nome;

    private String uuid;

    private Endereco enderecoPorId;
    private Endereco enderecoPorUuid;


    private List<Curso> cursoPorId;
    private List<Curso> cursoPorUuid;


    private List<Produto> produtoListComJoinTable;
    private List<Produto> produtoListComEmbeddable;


    private List<Pedido> pedidoListComJoinTable;
    private List<Pedido> pedidoListComEmbeddable;


}

