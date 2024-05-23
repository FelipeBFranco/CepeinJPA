package cepein.mapeamento.infra.adapters.http.forms;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoCommand;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EnderecoForms {

    private Long id;

    @Size(max = 250, message = "O campo 'descriçao' pode ter no máximo {MAX} caracteres!")
    private String uuid;

    @Size(max = 50, message = "O campo rua pode ter no máximo {max} caracteres!")
    private String rua;

    @Size(max = 10, message = "O campo cep pode ter no máximo {max} caracteres!")
    private String cep;

    @Size(max = 50, message = "O campo cidade pode ter no máximo {max} caracteres!")
    private String cidade;

    @Size(max = 2, message = "O campo estado pode ter no máximo {max} caracteres!")
    private String estado;

    public EnderecoCommand converter(EnderecoQuery enderecoQuery){
        EnderecoCommand enderecoCommand = new EnderecoCommand();
        enderecoCommand.setUuid(Objects.isNull(this.uuid) ? enderecoQuery.getUuid() : this.uuid);
        enderecoCommand.setRua(Objects.isNull(this.rua) ? enderecoQuery.getRua() : this.rua);
        enderecoCommand.setCep(Objects.isNull(this.cep) ? enderecoQuery.getCep() : this.cep);
        enderecoCommand.setCidade(Objects.isNull(this.cidade) ? enderecoQuery.getCidade() : this.cidade);
        enderecoCommand.setEstado(Objects.isNull(this.estado) ? enderecoQuery.getEstado() : this.estado);
        return enderecoCommand;
    }
    public EnderecoCommand converter(){
        EnderecoCommand enderecoCommand = new EnderecoCommand();
        enderecoCommand.setUuid(this.uuid);
        enderecoCommand.setRua(this.rua);
        enderecoCommand.setCep(this.cep);
        enderecoCommand.setCidade(this.cidade);
        enderecoCommand.setEstado(this.estado);
        return enderecoCommand;
    }
}
