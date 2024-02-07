package cepein.mapeamento.endereco.dto;

import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EnderecoDto {

    private Long idEndereco;

    private String uuid;

    private PessoaDtoParaRelacionamento pessoaListPorId;
    private PessoaDtoParaRelacionamento pessoaListPorUuid;

    public EnderecoDto(Endereco endereco){
        this.idEndereco = endereco.getIdEndereco();
        this.uuid = endereco.getUuid();
        this.pessoaListPorId = new PessoaDtoParaRelacionamento(endereco.getPessoaPorId());
        this.pessoaListPorUuid = new PessoaDtoParaRelacionamento(endereco.getPessoaPorUuid());
    }
    public static List<EnderecoDto> converter(List<Endereco> enderecoList){
        return enderecoList.stream()
                .map(EnderecoDto::new)
                .collect(Collectors.toList());
    }
}
