package cepein.mapeamento.pessoa.dto;

import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.pessoa.model.Pessoa;

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

    private String enderecoPorUuid;

    public static List<PessoaDto> convet(List<Pessoa> pessoaList){
        return pessoaList.stream()
                .map(pessoa -> new PessoaDto(pessoa.getId_Pessoa(),pessoa.getNome(),pessoa.getUuid(),pessoa.getEnderecoPorId(),pessoa.getEnderecoPorUuid()))
                .collect(Collectors.toList());
    }

}
