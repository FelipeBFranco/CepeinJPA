package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.infra.adapters.http.dtos.EnderecoDto;
import cepein.mapeamento.infra.adapters.http.dtos.PessoaDto;
import cepein.mapeamento.acore.domain.models.Endereco;
import cepein.mapeamento.acore.domain.models.Pessoa;

import java.util.Objects;

public class EnderecoViewModel {

    public static EnderecoDto toDto(Endereco endereco){
        return  EnderecoDto
                .builder()
                .idEndereco(endereco.getId_endereco())
                .uuid(endereco.getUuid())
                .rua(endereco.getRua())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .pessoaPorId(Objects.isNull(endereco.getPessoaPorId())?
                        null : PessoaViewModel.toDto(endereco.getPessoaPorId()))
                .pessoaPorUuid(Objects.isNull(endereco.getPessoaPorUuid())?
                        null :PessoaViewModel.toDto(endereco.getPessoaPorUuid()))
                .build();
    }

}
