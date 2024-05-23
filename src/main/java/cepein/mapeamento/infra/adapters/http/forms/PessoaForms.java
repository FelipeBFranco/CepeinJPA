package cepein.mapeamento.infra.adapters.http.forms;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaCommand;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
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
    private Long id;
    private String nome;

    private String uuid;

    private Long idEndereco;

    private String uuidEndereco;


    public PessoaCommand converter(PessoaQuery pessoaQuery){

        PessoaCommand pessoaCommand = new PessoaCommand();
        pessoaCommand.setNome(Objects.isNull(this.nome) ? pessoaQuery.getNome() : this.nome);
        pessoaCommand.setUuid(Objects.isNull(this.uuid) ? pessoaQuery.getUuid() : this.uuid);
        pessoaCommand.setIdEndereco(Objects.isNull(this.idEndereco) ?
                pessoaQuery.getEnderecoQueryPorId().getId_endereco() : this.idEndereco);
        pessoaCommand.setUuidEndereco(Objects.isNull(this.uuidEndereco) ?
                pessoaQuery.getEnderecoQueryPorUuid().getUuid() : this.uuidEndereco);
        return pessoaCommand;
    }
    public PessoaCommand converter(){
        PessoaCommand pessoaCommand = new PessoaCommand();
        pessoaCommand.setNome(this.nome);
        pessoaCommand.setUuid(this.uuid);
        pessoaCommand.setIdEndereco(this.idEndereco);
        pessoaCommand.setUuidEndereco(this.uuidEndereco);
        return pessoaCommand;
    }
}
