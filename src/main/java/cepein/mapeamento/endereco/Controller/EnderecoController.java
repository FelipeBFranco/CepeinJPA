package cepein.mapeamento.endereco.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cepein.mapeamento.endereco.Service.EnderecoService;
import cepein.mapeamento.endereco.model.Endereco;

@RequestMapping("/endereco")
@RestController
public class EnderecoController {

	@Autowired
	private EnderecoService servicoDeInformacoesEndereco;
	
	@GetMapping()
	public List<Endereco> getTodosEnderecos() {
		return this.servicoDeInformacoesEndereco.getTodosEnderecos();
	}
}
