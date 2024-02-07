package cepein.mapeamento.curso.forms;

import cepein.mapeamento.curso.model.Curso;
import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.pessoa.model.Pessoa;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CursoForms {

    @Size(max = 200, message = "O campo 'descriçao' pode ter no máximo {MAX} caracteres!")
    private String descricao;

    private Long pessoaId;

    @Size(max = 128, message = "O campo 'pessoaUuid' pode ter no máximo {MAX} caracteres!")
    private String pessoaUuuid;

    public Curso convet(Curso curso, Pessoa pessoa){
        curso.setDescricao(this.descricao);
        curso.setPessoaPorId(pessoa);
        curso.setPessoaPorUuid(pessoa);

        return curso;
    }
}
