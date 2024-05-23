package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.infra.adapters.http.dtos.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PessoaViewModel {

    public static List<PessoaDto> toDto (List<PessoaQuery> pessoaQueryList){
        return  pessoaQueryList
                .stream()
                .map(PessoaViewModel::toDto)
                .collect(Collectors.toList());
    }
    public static PessoaDto toDto(PessoaQuery pessoaQuery){
        return  PessoaDto.builder()
                .id(pessoaQuery.getId())
                .uuid(pessoaQuery.getUuid())
                .nome(pessoaQuery.getNome())

                .enderecoPorId(Objects.isNull(pessoaQuery.getEnderecoQueryPorId())?
                        null : EnderecoViewModel.toDto(pessoaQuery.getEnderecoQueryPorId()))
                .enderecoPorUuid(Objects.isNull(pessoaQuery.getEnderecoQueryPorUuid())?
                        null : EnderecoViewModel.toDto(pessoaQuery.getEnderecoQueryPorId()))

                .cursoListPorId(Objects.isNull(pessoaQuery.getCursoPorId())? null : pessoaQuery.getCursoPorId()
                                .stream()
                                .map(CursoViewModel::toDto)
                                .collect(Collectors.toList()))
                .cursoListPorUuid(Objects.isNull(pessoaQuery.getCursoPorUuid())? null : pessoaQuery.getCursoPorUuid()
                        .stream()
                        .map(CursoViewModel::toDto)
                        .collect(Collectors.toList()))


                .produtoListComJoinTable(Objects.isNull(pessoaQuery.getProdutoQueryListComJoinTable())?
                        null : pessoaQuery.getProdutoQueryListComJoinTable()
                                .stream()
                                .map(ProdutoViewModel::toDto)
                                .collect(Collectors.toList()))

                .produtoListComEmbeddable(Objects.isNull(pessoaQuery.getProdutoQueryListComEmbeddable())?
                        null : pessoaQuery.getProdutoQueryListComEmbeddable()
                        .stream()
                        .map(ProdutoViewModel::toDto)
                        .collect(Collectors.toList()))


                .pedidoListComJoinTable(Objects.isNull(pessoaQuery.getPedidoQueryListComJoinTable())?
                        null : pessoaQuery.getPedidoQueryListComJoinTable()
                                .stream()
                                .map(PedidoViewModel::toDto)
                                .collect(Collectors.toList()))

                .pedidoListComEmbeddable(Objects.isNull(pessoaQuery.getPedidoQueryListComEmbeddable())?
                        null : pessoaQuery.getPedidoQueryListComJoinTable()
                                .stream()
                                .map(PedidoViewModel::toDto)
                                .collect(Collectors.toList()))
                .build();
    }


  /*  public List<PessoaProduto> createPessoaProduto(PessoaQuery pessoa, List<ProdutoQuery> produtoList) {
        List<PessoaProduto> pessoaProdutoList = new ArrayList<>();
        produtoList.forEach(produto -> { pessoaProdutoList.add(new PessoaProduto(new PessoaProdutoId(pessoa.getId(), produto.getId()), pessoa, produto));});

        return pessoaProdutoList;
    }
    public List<PessoaProduto> createPessoaProduto(ProdutoQuery produto,List<PessoaQuery> pessoaList) {
        List<PessoaProduto> pessoaProdutoList = new ArrayList<>();
        pessoaList.forEach(pessoa -> { pessoaProdutoList.add(new PessoaProduto(new PessoaProdutoId(pessoa.getId(), produto.getId()), pessoa, produto));});

        return pessoaProdutoList;
    }*/
}
