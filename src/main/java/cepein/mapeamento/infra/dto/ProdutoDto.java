package cepein.mapeamento.infra.dto;

import cepein.mapeamento.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ProdutoDto {

    private Long id;

    private String Descricao;

    private List<PessoaDtoParaRelacionamento> pessoaListComJoinTable;
    private List<PessoaDtoParaRelacionamento> pessoaListComEmbeddable;
    public static List<ProdutoDto> converter(List<Produto> produtoList){
        return produtoList.stream()
                .map(produto -> new ProdutoDto(produto.getId(), produto.getDescricao(),
                        PessoaDtoParaRelacionamento.converter(produto.getPessoaListComJoinTable()),
                        PessoaDtoParaRelacionamento.converterPessoaProduto(produto.getPessoaListComEmbeddable())))
                .collect(Collectors.toList());
    }
}
