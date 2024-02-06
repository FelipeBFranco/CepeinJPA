package cepein.mapeamento.endereco.dto;

import cepein.mapeamento.endereco.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@AllArgsConstructor
public class EnderecoDtoParaRelacionamento {

    private Long idEndereco;

    private String uuid;

    public EnderecoDtoParaRelacionamento(Endereco endereco){
        this.idEndereco = endereco.getIdEndereco();
        this.uuid = endereco.getUuid();
}

}
