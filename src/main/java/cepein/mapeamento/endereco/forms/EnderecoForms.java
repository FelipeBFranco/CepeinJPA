package cepein.mapeamento.endereco.forms;

import cepein.mapeamento.endereco.model.Endereco;
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

    public Endereco converter(Endereco endereco){
        endereco.setRua(Objects.isNull(this.rua) ? endereco.getRua() : this.rua);
        endereco.setCep(Objects.isNull(this.cep) ? endereco.getCep() : this.cep);
        endereco.setCidade(Objects.isNull(this.cidade) ? endereco.getCidade() : this.cidade);
        endereco.setEstado(Objects.isNull(this.estado) ? endereco.getEstado() : this.estado);
        return endereco;
    }
}
