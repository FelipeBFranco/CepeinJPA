package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.acore.domain.models.Pedido;
import cepein.mapeamento.app.gateways.PedidoGateway;

import java.util.List;

public class EncontrarListaDePedidoUseCase {
    private PedidoGateway pedidoGateway;

    public EncontrarListaDePedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    public List<Pedido> encontrarListaPedido(){
        return this.pedidoGateway.encontrarTodosOsPedidos();
    }
}
