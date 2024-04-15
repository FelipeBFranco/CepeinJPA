package cepein.mapeamento.infra.service;

import cepein.mapeamento.infra.dto.PedidoDto;
import cepein.mapeamento.infra.forms.PedidoForms;
import cepein.mapeamento.model.Pedido;
import cepein.mapeamento.model.Pessoa;
import cepein.mapeamento.infra.repository.PedidoRepository;
import cepein.mapeamento.infra.repository.PessoaRepository;
import cepein.mapeamento.infra.repository.PessoaPedidoRepository;
import cepein.mapeamento.infra.repository.PessoaProdutoRepository;
import exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PessoaPedidoRepository pessoaPedidoRepository;
    private final PessoaRepository pessoaRepository;
    private final PessoaProdutoRepository pessoaProdutoRepository;
    PessoaFactory pessoaFactory = new PessoaFactory();
    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, PessoaPedidoRepository pessoaPedidoRepository, PessoaRepository pessoaRepository, PessoaProdutoRepository pessoaProdutoRepository){
        this.pedidoRepository = pedidoRepository;
        this.pessoaPedidoRepository = pessoaPedidoRepository;
        this.pessoaRepository = pessoaRepository;
        this.pessoaProdutoRepository = pessoaProdutoRepository;
    }

    public Pedido buscarPedido(Long idPedido){
        return this.pedidoRepository.findById(idPedido)
                .orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public List<PedidoDto> listarPedidos(){
        List<Pedido> pedidoList = this.pedidoRepository.findAll();
        List<PedidoDto> pedidoDtoList = PedidoDto.converter(pedidoList);
        return pedidoDtoList;
    }
    public PedidoDto procurarPedido(Long idPedido){
        Pedido pedido = this.buscarPedido(idPedido);
        PedidoDto pedidoDto = new PedidoDto(pedido);
        return pedidoDto;
    }

    @Transactional
    public void cadastrarPedido(PedidoForms pedidoForm) {

        Pedido pedidoNovo = pedidoForm.converter(new Pedido());
        List<Pessoa> pessoaList = this.pessoaRepository.findAllById(pedidoForm.getListaDosIdsDasPessoas());
        this.pedidoRepository.save(pedidoNovo);
        this.pessoaPedidoRepository.saveAll(pessoaFactory.createPessoaPedido(pedidoNovo,pessoaList));

    }

    @Transactional
    public void atualizarPedidoExistente(PedidoForms pedidoForms, Long idPedido) {
        Pedido pedido = pedidoForms.converter(this.buscarPedido(idPedido));
        this.pedidoRepository.save(pedido);
    }

    @Transactional
    public void deletarPedido(Long idPedido){
        this.pedidoRepository.deleteById(idPedido);
    }



}
