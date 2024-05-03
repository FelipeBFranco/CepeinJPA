package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.acore.domain.models.*;
import cepein.mapeamento.infra.adapters.http.dtos.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PessoaViewModel {

    public static List<PessoaDto> toDto (List<Pessoa> pessoaList){
        return  pessoaList
                .stream()
                .map(PessoaViewModel::toDto)
                .collect(Collectors.toList());
    }
    public static PessoaDto toDto(Pessoa pessoa){
        return  PessoaDto.builder()
                .id(pessoa.getId())
                .uuid(pessoa.getUuid())
                .nome(pessoa.getNome())

                .enderecoPorId(Objects.isNull(pessoa.getEnderecoPorId())?
                        null : EnderecoViewModel.toDto(pessoa.getEnderecoPorId()))
                .enderecoPorUuid(Objects.isNull(pessoa.getEnderecoPorUuid())?
                        null : EnderecoViewModel.toDto(pessoa.getEnderecoPorId()))

                .cursoListPorId(Objects.isNull(pessoa.getCursoPorId())? null : pessoa.getCursoPorId()
                                .stream()
                                .map(CursoViewModel::toDto)
                                .collect(Collectors.toList()))
                .cursoListPorUuid(Objects.isNull(pessoa.getCursoPorUuid())? null : pessoa.getCursoPorUuid()
                        .stream()
                        .map(CursoViewModel::toDto)
                        .collect(Collectors.toList()))


                .produtoListComJoinTable(Objects.isNull(pessoa.getProdutoListComJoinTable())?
                        null : pessoa.getProdutoListComJoinTable()
                                .stream()
                                .map(ProdutoViewModel::toDto)
                                .collect(Collectors.toList()))

                .produtoListComEmbeddable(Objects.isNull(pessoa.getProdutoListComEmbeddable())?
                        null :pessoa.getProdutoListComEmbeddable()
                        .stream()
                        .map(ProdutoViewModel::toDto)
                        .collect(Collectors.toList()))


                .pedidoListComJoinTable(Objects.isNull(pessoa.getPedidoListComJoinTable())?
                        null : pessoa.getPedidoListComJoinTable()
                                .stream()
                                .map(PedidoViewModel::toDto)
                                .collect(Collectors.toList()))

                .pedidoListComEmbeddable(Objects.isNull(pessoa.getPedidoListComEmbeddable())?
                        null : pessoa.getPedidoListComJoinTable()
                                .stream()
                                .map(PedidoViewModel::toDto)
                                .collect(Collectors.toList()))
                .build();
    }


  /*  public List<PessoaProduto> createPessoaProduto(Pessoa pessoa, List<Produto> produtoList) {
        List<PessoaProduto> pessoaProdutoList = new ArrayList<>();
        produtoList.forEach(produto -> { pessoaProdutoList.add(new PessoaProduto(new PessoaProdutoId(pessoa.getId(), produto.getId()), pessoa, produto));});

        return pessoaProdutoList;
    }
    public List<PessoaProduto> createPessoaProduto(Produto produto,List<Pessoa> pessoaList) {
        List<PessoaProduto> pessoaProdutoList = new ArrayList<>();
        pessoaList.forEach(pessoa -> { pessoaProdutoList.add(new PessoaProduto(new PessoaProdutoId(pessoa.getId(), produto.getId()), pessoa, produto));});

        return pessoaProdutoList;
    }*/
}
