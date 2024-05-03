package cepein.mapeamento.infra.adapters.http.forms;

import cepein.mapeamento.acore.domain.models.Curso;
import cepein.mapeamento.acore.domain.models.Pessoa;
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



    @Size(max = 200, message = "O campo 'descriçao' pode ter no máximo {MAX} caracteres!")
    private String descricao;

    private Long pessoaId;

    @Size(max = 128, message = "O campo 'pessoaUuid' pode ter no máximo {MAX} caracteres!")
    private String pessoaUuid;

    public Curso converter(Curso curso, Pessoa pessoa){

        curso.setDescricao(Objects.isNull(this.descricao) ? curso.getDescricao() : this.descricao);
        curso.setPessoaPorId(pessoa);
        curso.setPessoaPorUuid(pessoa);

        return curso;
    }
    public Curso converter( Pessoa pessoa){
        Curso curso = new Curso();
        curso.setDescricao(this.descricao);
        curso.setPessoaPorId(pessoa);
        curso.setPessoaPorUuid(pessoa);

        return curso;
    }
}
