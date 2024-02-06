package cepein.mapeamento.endereco.forms;

import cepein.mapeamento.endereco.model.Endereco;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnderecoForms {
    @Size(max = 250, message = "O campo 'descriçao' pode ter no máximo {MAX} caracteres!")
    private String uuid;





    public Endereco convet(Endereco endereco){
        endereco.setUuid(this.getUuid());
        return endereco;
    }
}
