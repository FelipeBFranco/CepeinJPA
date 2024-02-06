package cepein.mapeamento.pessoa.dto;


import cepein.mapeamento.pessoa.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@AllArgsConstructor
public class PessoaDtoParaRelacionamento {

    private Long id;

    private String nome;

    private String uuid;


    public PessoaDtoParaRelacionamento(Pessoa pessoa){
        this.id = pessoa.getId_pessoa();
        this.nome = pessoa.getNome();
        this.uuid = pessoa.getUuid();

    }
    public static List<PessoaDtoParaRelacionamento> convet(List<Pessoa> pessoaList){
        return pessoaList.stream()
                .map(PessoaDtoParaRelacionamento::new)
                .collect(Collectors.toList());
    }

}
