package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.infra.adapters.http.forms.PedidoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class CadastrarPedidoUseCase implements UseCaseRequest<PedidoForms> {
    private final PedidoGateway pedidoGateway;

    public CadastrarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    @Override
    public void execute(PedidoForms pedidoForms){
        this.pedidoGateway.salvar(pedidoForms.converter());
    }
}
