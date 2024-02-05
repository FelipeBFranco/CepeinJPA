package cepein.mapeamento.endereco.dto;

import cepein.mapeamento.endereco.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EnderecoDto {

    private Long id_endereco;

    private String uuid;


    public static List<EnderecoDto> convet(List<Endereco> enderecoList){
        return enderecoList.stream()
                .map(endereco -> new EnderecoDto(endereco.getIdEndereco(),endereco.getUuid()))
                .collect(Collectors.toList());
    }
}
