package cepein.mapeamento.pessoa.dto;

import cepein.mapeamento.curso.dto.CursoDtoParaRelacionamento;
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

    private Endereco enderecoPorId;

    private Endereco enderecoPorUuid;

    private List<CursoDtoParaRelacionamento> cursoListPorId;
    private List<CursoDtoParaRelacionamento> cursoListPorUuid;

    private List<ProdutoDtoParaRelacionamento> produtoListComJoinTable;

    private List<PedidoDtoParaRelacionamento> pedidoListComJoinTable;

    public PessoaDto (Pessoa pessoa){
        this.id = pessoa.getId_pessoa();
        this.nome = pessoa.getNome();
        this.uuid = pessoa.getUuid();
        this.enderecoPorId = pessoa.getEnderecoPorId();
        this.enderecoPorUuid = pessoa.getEnderecoPorUuid();
        this.cursoListPorId = CursoDtoParaRelacionamento.converter(pessoa.getCursoPorId());
        this.cursoListPorUuid = CursoDtoParaRelacionamento.converter(pessoa.getCursoPorUuid());
        this.produtoListComJoinTable = ProdutoDtoParaRelacionamento.converter(pessoa.getProdutoListComJoinTable());
        this.pedidoListComJoinTable = PedidoDtoParaRelacionamento.converter(pessoa.getPedidoListComJoinTable());

    }
    public static List<PessoaDto> convet(List<Pessoa> pessoaList){
        return pessoaList.stream()
                .map(PessoaDto::new)
                .collect(Collectors.toList());
    }

}
