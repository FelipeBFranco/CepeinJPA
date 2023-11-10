package cepein.mapeamento.endereco.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cepein.mapeamento.endereco.Repository.EnderecoRepository;
import cepein.mapeamento.endereco.model.Endereco;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repositorioInformacoesEndereco;

	public List<Endereco> getTodosEnderecos() {
		List<Endereco> enderecosEncontrados = this.repositorioInformacoesEndereco.findAll();
		return enderecosEncontrados;
	}
}
