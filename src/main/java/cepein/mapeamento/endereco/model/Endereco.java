package cepein.mapeamento.endereco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "endereco")
@Entity
public class Endereco {

	@Id
	@Column(name = "id_endereco")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    
	@Column(name = "uuid_endereco")
	@NotNull(message = " campo de {uuid_endereco} não pode estar nulo/vazio")
    private String uuidEndereco;
    
	@Column(name = "cidade_endereco")
	@NotNull(message = " campo de {cidade_endereco} não pode estar nulo/vazio")
    private String cidadeEndereco;
    
	@Column(name = "uf_endereco")
	@NotNull(message = " campo de {uf_endereco} não pode estar nulo/vazio")
    private String ufEndereco;
	
    }
