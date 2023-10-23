package cepein.mapeamento.pessoa.pessoa_pedido.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Getter
@Setter
public class Pessoa_pedido {
	@ManyToMany
	@JoinColumn(name = "uuid_pessoa")
	private UUID pessoa;
	
	private UUID pedido;
}