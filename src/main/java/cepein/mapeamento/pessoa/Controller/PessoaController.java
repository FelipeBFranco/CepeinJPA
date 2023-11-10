package cepein.mapeamento.pessoa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cepein.mapeamento.endereco.Service.EnderecoService;
import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService servicoDeInformacoesPessoas;
	
	@GetMapping()
	public List<Pessoa> getTodosEnderecos() {
		return this.servicoDeInformacoesPessoas.getTodasPessoas();
	}

}
