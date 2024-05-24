package cepein.mapeamento.app.usecases.pedido;

import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseRequest;

public class DeletarPedidoUseCase implements UseCaseRequest<Long> {
    private final PedidoGateway pedidoGateway;
    private Long idPedido;

    public DeletarPedidoUseCase(PedidoGateway pedidoGateway){
        this.pedidoGateway = pedidoGateway;
    }
    @Override
    public void execute(Long id){
        this.idPedido = id;
        this.verificarExistenciaPedido();
        this.pedidoGateway.deletar(id);
    }
    private void verificarExistenciaPedido(){
        this.pedidoGateway.buscar(idPedido);
    }
}
