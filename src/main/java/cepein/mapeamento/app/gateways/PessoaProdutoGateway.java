package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaProdutoCommand;
import cepein.mapeamento.utils.clean.application.gateway.SaveGateway;

import java.util.List;

public interface PessoaProdutoGateway extends SaveGateway<List<PessoaProdutoCommand>> {
}
