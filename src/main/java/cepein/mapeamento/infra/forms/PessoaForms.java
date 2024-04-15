package cepein.mapeamento.infra.forms;

import cepein.mapeamento.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaForms {

    private String nome;

    private String uuid;

    private List<Long> idListProduto;

    private List<Long> idListPedido;

    private EnderecoForms enderecoForms;

    public Pessoa converter(Pessoa pessoa){
        pessoa.setNome(Objects.isNull(this.nome) ? pessoa.getNome() : this.nome);
        pessoa.setUuid(Objects.isNull(this.uuid) ? pessoa.getUuid() : this.uuid);
        return pessoa;
    }
}
