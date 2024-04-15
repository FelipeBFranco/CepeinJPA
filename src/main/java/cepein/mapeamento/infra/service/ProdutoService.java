package cepein.mapeamento.infra.service;

import cepein.mapeamento.infra.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.model.Pessoa;
import cepein.mapeamento.infra.repository.PessoaRepository;
import cepein.mapeamento.infra.repository.PessoaProdutoRepository;
import cepein.mapeamento.infra.dto.ProdutoDto;
import cepein.mapeamento.infra.forms.ProdutoForms;
import cepein.mapeamento.model.Produto;
import cepein.mapeamento.infra.repository.ProdutoRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final PessoaRepository pessoaRepository;
    private final PessoaProdutoRepository pessoaProdutoRepository;
    PessoaFactory pessoaFactory = new PessoaFactory();

    public ProdutoService(ProdutoRepository produtoRepository, PessoaRepository pessoaRepository, PessoaProdutoRepository pessoaProdutoRepository){
        this.produtoRepository = produtoRepository;
        this.pessoaRepository = pessoaRepository;
        this.pessoaProdutoRepository = pessoaProdutoRepository;
    }

    public Produto buscarProduto(Long idProduto){
        return this.produtoRepository.findById(idProduto)
                .orElseThrow(()-> new ObjectNotFoundException("Produto não encontrado"));
    }

    public List<ProdutoDto> listarProdutos(){
        List<Produto> produtoList = this.produtoRepository.findAll();
        List<ProdutoDto> produtoDtoList = ProdutoDto.converter(produtoList);

        return produtoDtoList;
    }

    public ProdutoDto procurarProduto(Long idProduto){
        Produto produto = this.buscarProduto(idProduto);
        ProdutoDto produtoDto = new ProdutoDto(produto.getId(),
                produto.getDescricao(),
                PessoaDtoParaRelacionamento.converter(produto.getPessoaListComJoinTable()),
                PessoaDtoParaRelacionamento.converterPessoaProduto(produto.getPessoaListComEmbeddable()));
        return produtoDto;
    }

    @Transactional
    public void cadastrarProdutoComPessoa(ProdutoForms produtoForms) {

        Produto produtoNovo = produtoForms.converter(new Produto());
        List<Pessoa> pessoaList = this.pessoaRepository.findAllById(produtoForms.getListaDeIdsDasPessoas());
        this.produtoRepository.save(produtoNovo);
        this.pessoaProdutoRepository.saveAll(pessoaFactory.createPessoaProduto(produtoNovo,pessoaList));

    }

    @Transactional
    public void deletarProduto(Long idProduto){
        this.buscarProduto(idProduto);
        this.produtoRepository.deleteById(idProduto);
    }

    @Transactional
    public void atualizarProduto(ProdutoForms produtoForms, Long idProduto) {
        Produto produto = this.buscarProduto(idProduto);
        Produto produtoAlterado = produtoForms.converter(produto);
        this.produtoRepository.save(produtoAlterado);

    }
}
