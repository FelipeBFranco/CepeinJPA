package cepein.mapeamento.infra.adapters.http.viewmodels;


import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.infra.adapters.http.dtos.ProdutoDto;

import java.util.Objects;

public class ProdutoViewModel {
    public static ProdutoDto toDto(ProdutoQuery produtoQuery){
        return ProdutoDto.builder()
                .id(produtoQuery.getId())
                .descricao(produtoQuery.getDescricao())
                .pessoaListComJoinTable(Objects.isNull(produtoQuery.getPessoaQueryListComJoinTable())?
                        null : PessoaViewModel.toDto(produtoQuery.getPessoaQueryListComJoinTable()))
                .pessoaListComEmbeddable(Objects.isNull(produtoQuery.getPessoaQueryListComEmbeddable())?
                        null : PessoaViewModel.toDto(produtoQuery.getPessoaQueryListComEmbeddable()))
                .build();
    }
}