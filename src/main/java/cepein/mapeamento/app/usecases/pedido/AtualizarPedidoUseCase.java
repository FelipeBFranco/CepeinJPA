package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.infra.adapters.http.forms.PedidoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class AtualizarPedidoUseCase implements UseCaseRequest<PedidoForms> {
    private final PedidoGateway pedidoGateway;

    public AtualizarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    @Override
    public void execute(PedidoForms pedidoForms ){
        PedidoQuery pedidoQuery = this.pedidoGateway.buscar(pedidoForms.getId());
        this.pedidoGateway.salvar(pedidoForms.converter(pedidoQuery));
    }

}
