package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.utils.clean.application.useCase.UseCase;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseResponse;

import java.util.List;

public class EncontrarListaDePedidoUseCase implements UseCaseResponse<List<PedidoQuery>> {
    private final PedidoGateway pedidoGateway;

    public EncontrarListaDePedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    @Override
    public List<PedidoQuery> execute(){
        return this.pedidoGateway.encontrarTodosOsPedidos();
    }

}
