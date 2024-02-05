package cepein.mapeamento.curso.dto;

import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.curso.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CursoDto {
    private Long id;

    private String descricao;

    private Pessoa pessoaPorId;

    private Pessoa pessoaPorUuid;

    public static List<CursoDto> converter(List<Curso> cursos){
        return cursos.stream()
                .map(curso -> new CursoDto(curso.getId_curso(), curso.getDescricao(), curso.getPessoaPorId(), curso.getPessoaPorUuid()))
                .collect(Collectors.toList());
    }
}
