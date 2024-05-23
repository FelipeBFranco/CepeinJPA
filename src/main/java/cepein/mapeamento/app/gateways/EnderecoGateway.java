package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.endereco.EnderecoCommand;
import cepein.mapeamento.acore.domain.models.endereco.EnderecoQuery;
import cepein.mapeamento.utils.clean.application.gateway.StorageGateway;

import java.util.List;

public interface EnderecoGateway extends StorageGateway<EnderecoCommand,EnderecoQuery,Long> {

    List<EnderecoQuery> encontrarTodosOsEnderecos();
}
