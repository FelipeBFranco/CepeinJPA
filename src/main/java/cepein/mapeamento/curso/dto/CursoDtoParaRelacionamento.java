package cepein.mapeamento.curso.dto;

import cepein.mapeamento.curso.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CursoDtoParaRelacionamento {
    private Long id;

    private String descricao;


    public static List<CursoDtoParaRelacionamento> converter(List<Curso> cursos){
        return cursos.stream()
                .map(curso -> new CursoDtoParaRelacionamento(curso.getId(), curso.getDescricao()))
                .collect(Collectors.toList());
    }
}
