package cepein.mapeamento.infra.adapters.http.viewmodels;


import cepein.mapeamento.acore.domain.models.Produto;
import cepein.mapeamento.infra.adapters.http.dtos.ProdutoDto;

import java.util.Objects;

public class ProdutoViewModel {
    public static ProdutoDto toDto(Produto produto){
        return ProdutoDto.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .pessoaListComJoinTable(Objects.isNull(produto.getPessoaListComJoinTable())?
                        null : PessoaViewModel.toDto(produto.getPessoaListComJoinTable()))
                .pessoaListComEmbeddable(Objects.isNull(produto.getPessoaListComEmbeddable())?
                        null : PessoaViewModel.toDto(produto.getPessoaListComEmbeddable()))
                .build();
    }
}