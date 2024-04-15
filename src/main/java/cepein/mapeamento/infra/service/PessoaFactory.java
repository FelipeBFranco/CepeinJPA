package cepein.mapeamento.infra.service;

import cepein.mapeamento.model.*;

import java.util.ArrayList;
import java.util.List;
public class PessoaFactory {
    public List<PessoaPedido> createPessoaPedido(Pessoa pessoa, List<Pedido> pedidoList) {
        List<PessoaPedido> pessoaPedidoList = new ArrayList<>();
        pedidoList.forEach(pedido -> { pessoaPedidoList.add(new PessoaPedido(new PessoaPedidoId(pessoa.getUuid(), pedido.getUuid()),pessoa, pedido));});

        return pessoaPedidoList;
    }
    public List<PessoaPedido> createPessoaPedido(Pedido pedido,List<Pessoa> pessoaList) {
        List<PessoaPedido> pessoaPedidoList = new ArrayList<>();
        pessoaList.forEach(pessoa -> { pessoaPedidoList.add(new PessoaPedido(new PessoaPedidoId(pessoa.getUuid(), pedido.getUuid()),pessoa, pedido));});
        return  pessoaPedidoList;
    }
    public List<PessoaProduto> createPessoaProduto(Pessoa pessoa, List<Produto> produtoList) {
        List<PessoaProduto> pessoaProdutoList = new ArrayList<>();
        produtoList.forEach(produto -> { pessoaProdutoList.add(new PessoaProduto(new PessoaProdutoId(pessoa.getId(), produto.getId()), pessoa, produto));});

        return pessoaProdutoList;
    }
    public List<PessoaProduto> createPessoaProduto(Produto produto,List<Pessoa> pessoaList) {
        List<PessoaProduto> pessoaProdutoList = new ArrayList<>();
        pessoaList.forEach(pessoa -> { pessoaProdutoList.add(new PessoaProduto(new PessoaProdutoId(pessoa.getId(), produto.getId()), pessoa, produto));});

        return pessoaProdutoList;
    }
}
