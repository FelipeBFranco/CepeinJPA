package cepein.mapeamento.curso.dto;

import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
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

    private PessoaDtoParaRelacionamento pessoaPorId;

    private PessoaDtoParaRelacionamento pessoaPorUuid;
    public CursoDto(Curso curso){
        this.id = curso.getId_curso();
        this.descricao = curso.getDescricao();
        this.pessoaPorId = new PessoaDtoParaRelacionamento(curso.getPessoaPorId());
        this.pessoaPorUuid = new PessoaDtoParaRelacionamento(curso.getPessoaPorUuid());

    }
    public static List<CursoDto> converter(List<Curso> cursos){
        return cursos.stream()
                .map(CursoDto::new)
                .collect(Collectors.toList());
    }
}
