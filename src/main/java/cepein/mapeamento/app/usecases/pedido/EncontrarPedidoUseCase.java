package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.utils.clean.application.useCase.UseCase;

public class EncontrarPedidoUseCase implements UseCase<Long,PedidoQuery> {
    private final PedidoGateway pedidoGateway;
    public EncontrarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    @Override
    public PedidoQuery execute(Long id){
        return this.pedidoGateway.buscar(id);
    }
}
