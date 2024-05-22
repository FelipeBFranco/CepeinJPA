package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.acore.domain.models.Pedido;
import cepein.mapeamento.app.gateways.PedidoGateway;

public class CadastrarPedidoUseCase {
    private PedidoGateway pedidoGateway;

    public CadastrarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    public void criarPedido(Pedido pedido){
        this.pedidoGateway.salvarPedido(pedido);
    }
}
