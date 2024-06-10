package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.pedido.PedidoCommand;
import cepein.mapeamento.acore.domain.models.pedido.PedidoQuery;
import cepein.mapeamento.utils.clean.application.gateway.StorageGateway;

import java.util.List;

public interface PedidoGateway extends StorageGateway<PedidoCommand,PedidoQuery,Long> {
    PedidoQuery buscar(String uuid);
    List<PedidoQuery> encontrarTodosOsPedidos();


}