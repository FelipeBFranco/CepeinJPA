package cepein.mapeamento.pessoa.forms;

import cepein.mapeamento.endereco.forms.EnderecoForms;
import cepein.mapeamento.pessoa.model.Pessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class PessoaForms {

    private String nome;

    private String uuid;

    private List<Long> pedidoList;

    private EnderecoForms enderecoForms;

    public Pessoa converter(Pessoa pessoa){
        pessoa.setNome(Objects.isNull(this.nome) ? pessoa.getNome() : this.nome);
        pessoa.setUuid(Objects.isNull(this.uuid) ? pessoa.getUuid() : this.uuid);
        return pessoa;
    }
}
