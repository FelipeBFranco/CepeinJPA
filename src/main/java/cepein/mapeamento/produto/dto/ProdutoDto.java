package cepein.mapeamento.produto.dto;

import cepein.mapeamento.produto.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ProdutoDto {

    private Long id;

    private String Descricao;

    public static List<ProdutoDto> converter(List<Produto> produtoList){
        return produtoList.stream()
                .map(produto -> new ProdutoDto(produto.getId(), produto.getDescricao()))
                .collect(Collectors.toList());
    }
}
