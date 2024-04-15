package cepein.mapeamento.infra.dto;

import cepein.mapeamento.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EnderecoDto {

    private Long idEndereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

    private PessoaDtoParaRelacionamento pessoaPorId;
    private PessoaDtoParaRelacionamento pessoaPorUuid;

    public EnderecoDto(Endereco endereco){
        this.idEndereco = endereco.getId_endereco();
        this.uuid = endereco.getUuid();
        this.rua = endereco.getRua();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.pessoaPorId = new PessoaDtoParaRelacionamento(endereco.getPessoaPorId());
        this.pessoaPorUuid = new PessoaDtoParaRelacionamento(endereco.getPessoaPorUuid());
    }
    public static List<EnderecoDto> converter(List<Endereco> enderecoList){
        return enderecoList.stream()
                .map(EnderecoDto::new)
                .collect(Collectors.toList());
    }
}
