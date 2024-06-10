package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.infra.adapters.http.dtos.EnderecoDto;

import java.util.Objects;

public class EnderecoViewModel {

    public static EnderecoDto toDto(EnderecoQuery enderecoQuery){
        return  EnderecoDto
                .builder()
                .idEndereco(enderecoQuery.getId_endereco())
                .uuid(enderecoQuery.getUuid())
                .rua(enderecoQuery.getRua())
                .cep(enderecoQuery.getCep())
                .cidade(enderecoQuery.getCidade())
                .estado(enderecoQuery.getEstado())
                .pessoaPorId(Objects.isNull(enderecoQuery.getPessoaQueryPorId())?
                        null : PessoaViewModel.toDto(enderecoQuery.getPessoaQueryPorId()))
                .pessoaPorUuid(Objects.isNull(enderecoQuery.getPessoaQueryPorUuid())?
                        null :PessoaViewModel.toDto(enderecoQuery.getPessoaQueryPorUuid()))
                .build();
    }

}
