package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.app.gateways.PedidoGateway;

public class DeletarPedidoUseCase {
    private PedidoGateway pedidoGateway;

    public DeletarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }

    public void deletarPedido(Long id){
        this.pedidoGateway.deletarPedidoPorId(id);
    }
}
