package cepein.mapeamento.infra.service;

import cepein.mapeamento.infra.repository.*;
import cepein.mapeamento.model.*;
import cepein.mapeamento.infra.dto.PessoaDto;
import cepein.mapeamento.infra.forms.PessoaForms;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository ;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final PessoaProdutoRepository pessoaProdutoRepository;
    private final PessoaPedidoRepository pessoaPedidoRepository;
    private final EnderecoRepository enderecoRepository;

    PessoaFactory pessoaFactory = new PessoaFactory();



    @Autowired
    public PessoaService(PessoaRepository pessoaRepository
            ,ProdutoRepository produtoRepository
            ,PessoaProdutoRepository pessoaProdutoRepository
            ,PedidoRepository pedidoRepository
            ,PessoaPedidoRepository pessoaPedidoRepository
            ,EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.produtoRepository = produtoRepository;
        this.pessoaProdutoRepository = pessoaProdutoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pessoaPedidoRepository = pessoaPedidoRepository;
        this.enderecoRepository = enderecoRepository;

    }

    public Pessoa buscarPessoa(Long idPessoa){
        return this.pessoaRepository.findById(idPessoa)
                .orElseThrow(()-> new ObjectNotFoundException("Pessoa não encontrada"));
    }

    public List<PessoaDto> listarPessoas(){
        List<Pessoa> pessoaList = this.pessoaRepository.findAll();
        List<PessoaDto> pessoaDtoList = PessoaDto.converter(pessoaList);
        return pessoaDtoList;
    }
    public PessoaDto procurarPessoa(Long idPessoa){
        Pessoa pessoa = this.buscarPessoa(idPessoa);
        PessoaDto pessoaDto = new PessoaDto(pessoa);

        return pessoaDto;
    }


    @Transactional
    public void deletarPessoa(Long idPessoa){
        this.buscarPessoa(idPessoa);
        this.pessoaRepository.deleteById(idPessoa);

        this.pessoaProdutoRepository.deleteAll();
    }

    @Transactional
    public void cadastrarPessoaComProduto(PessoaForms pessoaForms) {

        Pessoa pessoa = pessoaForms.converter(new Pessoa());

        Endereco enderecoNovo = pessoaForms.getEnderecoForms().converter(new Endereco());



        pessoa.setEnderecoPorId(enderecoNovo);
        pessoa.setEnderecoPorUuid(enderecoNovo);

        this.pessoaRepository.save(pessoa);

        this.salvarDadosProdutos(pessoaForms, pessoa);
        this.salvarDadosPedidos(pessoaForms,pessoa);

    }

    @Transactional
    public void atualizarPessoa(PessoaForms pessoaForms, Long idPessoa) {
        Pessoa pessoa = this.buscarPessoa(idPessoa);
        pessoa = pessoaForms.converter(pessoa);

        this.salvarDadosProdutos(pessoaForms, pessoa);
        this.salvarDadosPedidos(pessoaForms,pessoa);

        this.pessoaRepository.save(pessoa);
    }

    @Transactional
    public void salvarDadosProdutos(PessoaForms pessoaForms, Pessoa pessoa) {

        List<Produto> produtoList = this.produtoRepository.findAllById(pessoaForms.getIdListProduto());

        this.pessoaProdutoRepository.saveAll(pessoaFactory.createPessoaProduto(pessoa,produtoList));
    }
    @Transactional
    public void salvarDadosPedidos(PessoaForms pessoaForms, Pessoa pessoa) {

        List<Pedido> pedidoList = this.pedidoRepository.findAllById(pessoaForms.getIdListPedido());

        this.pessoaPedidoRepository.saveAll(pessoaFactory.createPessoaPedido(pessoa,pedidoList));
    }
}
