package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.infra.adapters.http.dtos.CursoDto;

import java.util.Objects;

public class CursoViewModel {

    public static CursoDto toDto(CursoQuery curso){
        return CursoDto
                .builder()
                .id(curso.getId())
                .descricao(curso.getDescricao())
                .pessoaPorId(Objects.isNull(curso.getPessoaQueryPorId())?
                        null : PessoaViewModel.toDto(curso.getPessoaQueryPorId()))
                .pessoaPorUuid(Objects.isNull(curso.getPessoaQueryPorUuid())?
                        null : PessoaViewModel.toDto(curso.getPessoaQueryPorUuid()))
                .build();
    }
}
