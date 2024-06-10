package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaPedidoCommand;
import cepein.mapeamento.utils.clean.application.gateway.SaveGateway;

import java.util.List;

public interface PessoaPedidoGateway extends SaveGateway<List<PessoaPedidoCommand>> {
}
