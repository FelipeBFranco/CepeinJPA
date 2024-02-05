package cepein.mapeamento.pessoa.dto;

import cepein.mapeamento.curso.dto.CursoDtoParaPessoa;
import cepein.mapeamento.curso.model.Curso;
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

    private Endereco enderecoPorUuid;

    private List<CursoDtoParaPessoa> cursoListPorId;
    private List<CursoDtoParaPessoa> cursoListPorUuid;

    public PessoaDto (Pessoa pessoa){
        this.id = pessoa.getId_pessoa();
        this.nome = pessoa.getNome();
        this.uuid = pessoa.getUuid();
        this.enderecoPorId = pessoa.getEnderecoPorId();
        this.enderecoPorUuid = pessoa.getEnderecoPorUuid();
        this.cursoListPorId = CursoDtoParaPessoa.converter(pessoa.getCursoPorId());
        this.cursoListPorUuid = CursoDtoParaPessoa.converter(pessoa.getCursoPorUuid());
    }
    public static List<PessoaDto> convet(List<Pessoa> pessoaList){
        return pessoaList.stream()
                .map(PessoaDto::new)
                .collect(Collectors.toList());
    }

}
