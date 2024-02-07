package cepein.mapeamento.pessoa.dto;

import cepein.mapeamento.curso.dto.CursoDtoParaRelacionamento;
import cepein.mapeamento.endereco.dto.EnderecoDtoParaRelacionamento;
import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.pedido.dto.PedidoDtoParaRelacionamento;
import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pessoa.model.Pessoa;

import cepein.mapeamento.pessoa_produto.PessoaProduto;
import cepein.mapeamento.produto.dto.ProdutoDtoParaRelacionamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PessoaDto {

    private Long id;

    private String nome;

    private String uuid;

    private EnderecoDtoParaRelacionamento enderecoPorId;

    private EnderecoDtoParaRelacionamento enderecoPorUuid;

    private List<CursoDtoParaRelacionamento> cursoListPorId;
    private List<CursoDtoParaRelacionamento> cursoListPorUuid;

    private List<ProdutoDtoParaRelacionamento> produtoListComJoinTable;
    private List<ProdutoDtoParaRelacionamento> produtoListComEmbeddable;

    private List<PedidoDtoParaRelacionamento> pedidoListComJoinTable;
    private List<PedidoDtoParaRelacionamento> pedidoListComEmbeddable;



    public PessoaDto (Pessoa pessoa){
        this.id = pessoa.getId_pessoa();
        this.nome = pessoa.getNome();
        this.uuid = pessoa.getUuid();
        this.enderecoPorId = new EnderecoDtoParaRelacionamento( pessoa.getEnderecoPorId());
        this.enderecoPorUuid = new EnderecoDtoParaRelacionamento( pessoa.getEnderecoPorUuid());
        this.cursoListPorId = CursoDtoParaRelacionamento.converter(pessoa.getCursoPorId());
        this.cursoListPorUuid = CursoDtoParaRelacionamento.converter(pessoa.getCursoPorUuid());
        this.produtoListComJoinTable = ProdutoDtoParaRelacionamento.converter(pessoa.getProdutoListComJoinTable());
        this.produtoListComEmbeddable =ProdutoDtoParaRelacionamento.converterPessoaProduto(pessoa.getProdutoListComEmbeddable());
        this.pedidoListComJoinTable = PedidoDtoParaRelacionamento.converter(pessoa.getPedidoListComJoinTable());
        this.pedidoListComEmbeddable = PedidoDtoParaRelacionamento.converterPessoaPedido(pessoa.getPedidoListComEmbeddable());

    }
    public static List<PessoaDto> convet(List<Pessoa> pessoaList){
        return pessoaList.stream()
                .map(PessoaDto::new)
                .collect(Collectors.toList());
    }

}
