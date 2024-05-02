package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.acore.domain.models.Pedido;
import cepein.mapeamento.app.gateways.PedidoGateway;

public class AtualizarPedidoUseCase {
    private PedidoGateway pedidoGateway;

    public AtualizarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    public void atualizarPedido(Pedido pedido){
        this.pedidoGateway.salvarPedido(pedido);
    }

}
