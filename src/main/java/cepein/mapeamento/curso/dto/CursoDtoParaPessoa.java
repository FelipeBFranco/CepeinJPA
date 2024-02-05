package cepein.mapeamento.curso.dto;

import cepein.mapeamento.curso.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CursoDtoParaPessoa {
    private Long id;

    private String descricao;


    public static List<CursoDtoParaPessoa> converter(List<Curso> cursos){
        return cursos.stream()
                .map(curso -> new CursoDtoParaPessoa(curso.getId_curso(), curso.getDescricao()))
                .collect(Collectors.toList());
    }
}
