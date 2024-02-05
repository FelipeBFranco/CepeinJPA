package cepein.mapeamento.pessoa.dto;


import cepein.mapeamento.pessoa.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@AllArgsConstructor
public class PessoaDtoParaCurso {
    private Long id;

    private String nome;

    private String uuid;


    public PessoaDtoParaCurso (Pessoa pessoa){
        this.id = pessoa.getId_pessoa();
        this.nome = pessoa.getNome();
        this.uuid = pessoa.getUuid();

    }
    public static List<PessoaDtoParaCurso> convet(List<Pessoa> pessoaList){
        return pessoaList.stream()
                .map(PessoaDtoParaCurso::new)
                .collect(Collectors.toList());
    }

}
