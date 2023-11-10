package cepein.mapeamento.pessoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
    private PessoaRepository repositorioInformacoesPessoas;
	
	public List<Pessoa> getTodasPessoas() {
		List<Pessoa> pessoasEncontrados = this.repositorioInformacoesPessoas.findAll();
		return pessoasEncontrados;
	}

}