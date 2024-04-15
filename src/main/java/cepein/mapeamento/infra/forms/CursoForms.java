package cepein.mapeamento.infra.forms;

import cepein.mapeamento.model.Curso;
import cepein.mapeamento.model.Pessoa;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoForms {



    @Size(max = 200, message = "O campo 'descriçao' pode ter no máximo {MAX} caracteres!")
    private String descricao;

    private Long pessoaId;

    @Size(max = 128, message = "O campo 'pessoaUuid' pode ter no máximo {MAX} caracteres!")
    private String pessoaUuid;

    public Curso converter(Curso curso, Pessoa pessoa){

        curso.setDescricao(this.descricao);
        curso.setPessoaPorId(pessoa);
        curso.setPessoaPorUuid(pessoa);

        return curso;
    }
}
