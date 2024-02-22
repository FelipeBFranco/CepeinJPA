package cepein.mapeamento.pessoa.service;

import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.endereco.repository.EnderecoRepository;
import cepein.mapeamento.pessoa.dto.PessoaDto;
import cepein.mapeamento.pessoa.forms.PessoaForms;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import cepein.mapeamento.pessoa_produto.PessoaProduto;
import cepein.mapeamento.pessoa_produto.PessoaProdutoId;
import cepein.mapeamento.pessoa_produto.repository.PessoaProdutoRepository;
import cepein.mapeamento.produto.model.Produto;
import cepein.mapeamento.produto.repository.ProdutoRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository ;
    private final ProdutoRepository produtoRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaProdutoRepository pessoaProdutoRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, ProdutoRepository produtoRepository, PessoaProdutoRepository pessoaProdutoRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.produtoRepository = produtoRepository;
        this.pessoaProdutoRepository = pessoaProdutoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Pessoa buscarPessoa(Long idPessoa){
        return this.pessoaRepository.findById(idPessoa)
                .orElseThrow(()-> new ObjectNotFoundException("Pessoa não encontrada"));
    }

    public ResponseEntity<List<PessoaDto>> listarPessoas(){
        List<Pessoa> pessoaList = this.pessoaRepository.findAll();
        List<PessoaDto> pessoaDtoList = PessoaDto.converter(pessoaList);
        return ResponseEntity.ok(pessoaDtoList);
    }
    public ResponseEntity<PessoaDto> procurarPessoa(Long idPessoa){
        Pessoa pessoa = this.buscarPessoa(idPessoa);
        PessoaDto pessoaDto = new PessoaDto(pessoa);

        return ResponseEntity.ok(pessoaDto);
    }


    @Transactional
    public ResponseEntity<Void> deletarPessoa(Long idPessoa){
        this.pessoaRepository.deleteById(idPessoa);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<HttpStatus> cadastrarPessoaComProduto(PessoaForms pessoaForms) {
        Pessoa pessoaNova = pessoaForms.converter(new Pessoa());

        Endereco enderecoNovo = pessoaForms.getEnderecoForms().converter(new Endereco());
        this.enderecoRepository.save(enderecoNovo);

        pessoaNova.setEnderecoPorId(enderecoNovo);
        pessoaNova.setEnderecoPorUuid(enderecoNovo);

        pegarListaDeIds(pessoaForms, pessoaNova);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<HttpStatus> atualizarPessoaExistente(PessoaForms pessoaForms, Long idPessoa) {
        Pessoa pessoa = this.buscarPessoa(idPessoa);
        pessoa = pessoaForms.converter(pessoa);

        pegarListaDeIds(pessoaForms, pessoa);

        this.pessoaRepository.save(pessoa);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void pegarListaDeIds(PessoaForms pessoaForms, Pessoa pessoa) {
        List<Long> listaDeIdsDosProdutos = this.produtoRepository.findAllById(pessoaForms.getPedidoList())
                .stream().map(Produto::getId).collect(Collectors.toList());

        listaDeIdsDosProdutos.forEach(t -> {
            this.pessoaProdutoRepository.save(new PessoaProduto
                    (new PessoaProdutoId(pessoa.getId(), t), pessoa, this.produtoRepository.findById(t).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado!"))));

        });
    }
}
