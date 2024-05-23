package cepein.mapeamento.infra.adapters.http.forms;


import cepein.mapeamento.acore.domain.models.curso.CursoCommand;
import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoForms {

    private Long id;

    @Size(max = 200, message = "O campo 'descriçao' pode ter no máximo {MAX} caracteres!")
    private String descricao;

    private Long idPessoa;

    @Size(max = 128, message = "O campo 'pessoaUuid' pode ter no máximo {MAX} caracteres!")
    private String uuidPessoa;

    public CursoCommand converter(CursoQuery cursoQuery){
        CursoCommand curso = new CursoCommand();
        curso.setDescricao(Objects.isNull(this.descricao) ? cursoQuery.getDescricao() : this.descricao);
        curso.setIdPessoa(Objects.isNull(this.descricao) ? cursoQuery.getPessoaQueryPorId().getId() :this.idPessoa);
        curso.setUuidPessoa(Objects.isNull(this.descricao) ? cursoQuery.getPessoaQueryPorUuid().getUuid() :this.uuidPessoa);

        return curso;
    }
    public CursoCommand converter(){
        CursoCommand curso = new CursoCommand();
        curso.setDescricao(this.descricao);
        curso.setIdPessoa(this.idPessoa);
        curso.setUuidPessoa(this.uuidPessoa);

        return curso;
    }
}
