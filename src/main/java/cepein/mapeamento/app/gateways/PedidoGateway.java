package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.Pedido;

import java.util.List;

public interface PedidoGateway {
    Pedido encontrarPedidoPorId(Long id);
    List<Pedido> encontrarTodosOsPedidos();
    void salvarPedido(Pedido pedido);
    void deletarPedidoPorId(Long id);
}