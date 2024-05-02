package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.acore.domain.models.Pedido;
import cepein.mapeamento.app.gateways.PedidoGateway;

public class EncontrarPedidoUseCase {
    private PedidoGateway pedidoGateway;
    public EncontrarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    public Pedido encontrarPedidoPorId(Long id){
        return this.pedidoGateway.encontrarPedidoPorId(id);
    }
}
