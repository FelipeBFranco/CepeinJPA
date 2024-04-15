package cepein.mapeamento.infra.dto;

import cepein.mapeamento.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnderecoDtoParaRelacionamento {

    private Long idEndereco;

    private String uuid;

    private String rua;

    private String cep;

    private String cidade;

    private String estado;

    public EnderecoDtoParaRelacionamento(Endereco endereco){
        this.idEndereco = endereco.getId_endereco();
        this.uuid = endereco.getUuid();
        this.rua = endereco.getRua();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
}

}
