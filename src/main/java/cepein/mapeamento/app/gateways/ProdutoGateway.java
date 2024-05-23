package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.produto.ProdutoCommand;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import cepein.mapeamento.utils.clean.application.gateway.StorageGateway;

import java.util.List;

public interface  ProdutoGateway extends StorageGateway<ProdutoCommand,ProdutoQuery,Long> {
    List<ProdutoQuery> encontrarTodasOsProduto();

}
