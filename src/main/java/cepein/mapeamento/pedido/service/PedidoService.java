package cepein.mapeamento.pedido.service;

import cepein.mapeamento.pedido.dto.PedidoDto;
import cepein.mapeamento.pedido.forms.PedidoForms;
import cepein.mapeamento.pedido.model.Pedido;
import cepein.mapeamento.pedido.repository.PedidoRepository;
import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import cepein.mapeamento.pessoa_pedido.PessoaPedido;
import cepein.mapeamento.pessoa_pedido.PessoaPedidoId;
import cepein.mapeamento.pessoa_pedido.repository.PessoaPedidoRepository;
import cepein.mapeamento.pessoa_produto.PessoaProduto;
import cepein.mapeamento.pessoa_produto.PessoaProdutoId;
import cepein.mapeamento.pessoa_produto.repository.PessoaProdutoRepository;
import cepein.mapeamento.produto.model.Produto;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PessoaPedidoRepository pessoaPedidoRepository;
    private final PessoaProdutoRepository pessoaProdutoRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, PessoaPedidoRepository pessoaPedidoRepository, PessoaRepository pessoaRepository, PessoaProdutoRepository pessoaProdutoRepository){
        this.pedidoRepository = pedidoRepository;
        this.pessoaPedidoRepository = pessoaPedidoRepository;
        this.pessoaRepository = pessoaRepository;
        this.pessoaProdutoRepository = pessoaProdutoRepository;
    }

    private Pedido buscarPedido(Long idPedido){
        return this.pedidoRepository.findById(idPedido)
                .orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public ResponseEntity<List<PedidoDto>> listarPedidos(){
        List<Pedido> pedidoList = this.pedidoRepository.findAll();
        List<PedidoDto> pedidoDtoList = PedidoDto.converter(pedidoList);
        return ResponseEntity.ok(pedidoDtoList);
    }
    public ResponseEntity<PedidoDto> procurarPedido(Long idPedido){
        Pedido pedido = this.buscarPedido(idPedido);
        PedidoDto pedidoDto = new PedidoDto(pedido.getId(),
                pedido.getDescricao(),
                pedido.getUuid(),
                PessoaDtoParaRelacionamento.converter(pedido.getPessoaListComJoinTable()),
                PessoaDtoParaRelacionamento.converterPessoaPedido(pedido.getPessoaListComEmbeddable()));
        return ResponseEntity.ok(pedidoDto);
    }

    @Transactional
    public ResponseEntity<HttpStatus> cadastrarPedido(PedidoForms pedidoForm) {


//        List<String> listaDeUuidDasPessoas = this.pessoaRepository
//                .findAllById(pedidoForm.getListaDosIdsDasPessoas())
//                .stream().map(Pessoa::getUuid).collect(Collectors.toList());
//
//        listaDeUuidDasPessoas.forEach(x -> {
//            PessoaPedidoId teste = new PessoaPedidoId(pedidoForm.getUuid(), x);
//
//            PessoaPedido teste2 = new PessoaPedido(5L,teste, this.pessoaRepository.findByUuid(x), new Pedido());
//
//            this.pessoaPedidoRepository.save(teste2);
//        });
//        List<Long> listaDeIdDasPessoas = this.pessoaRepository
//                .findAllById(pedidoForm.getListaDosIdsDasPessoas())
//                .stream().map(Pessoa::getId).collect(Collectors.toList());
//
//
//        listaDeIdDasPessoas.forEach(x -> {
//            PessoaProdutoId testeZinho = new PessoaProdutoId();
//            testeZinho.setIdPessoa(x);
//            testeZinho.setIdProduto(10L);
//            PessoaProduto testeZao = new PessoaProduto(testeZinho, this.pessoaRepository.findById(x).orElseThrow(), new Produto());
//
//            this.pessoaProdutoRepository.save(testeZao);
//        });



        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<HttpStatus> atualizarPedidoExistente(PedidoForms pedidoForms, Long idPedido) {
        Pedido pedido = this.buscarPedido(idPedido);
        pedido.setUuid(pedidoForms.getUuid() != null ? pedidoForms.getUuid() : pedido.getUuid());
        pedido.setDescricao(pedidoForms.getDescricao() != null ? pedidoForms.getDescricao() : pedido.getDescricao());
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<Void> deletarPedido(Long idPedido){
        this.pedidoRepository.deleteById(idPedido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
