package cepein.mapeamento.infra.adapters.http.viewmodels;

import cepein.mapeamento.infra.adapters.http.dtos.CursoDto;
import cepein.mapeamento.infra.adapters.http.dtos.PessoaDto;
import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.acore.domain.models.Pessoa;

import java.util.Objects;

public class CursoViewModel {

    public static CursoDto toDto(Curso curso){
        return CursoDto
                .builder()
                .id(curso.getId())
                .descricao(curso.getDescricao())
                .pessoaPorId(Objects.isNull(curso.getPessoaPorId())?
                        null : PessoaViewModel.toDto(curso.getPessoaPorId()))
                .pessoaPorUuid(Objects.isNull(curso.getPessoaPorUuid())?
                        null : PessoaViewModel.toDto(curso.getPessoaPorUuid()))
                .build();
    }
}
