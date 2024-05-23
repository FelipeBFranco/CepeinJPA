package cepein.mapeamento.app.gateways;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaCommand;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaPedidoCommand;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaProdutoCommand;
import cepein.mapeamento.acore.domain.models.pessoa.PessoaQuery;
import cepein.mapeamento.utils.clean.application.gateway.StorageGateway;

import java.util.List;

public interface PessoaGatway extends StorageGateway<PessoaCommand,PessoaQuery,Long> {
    PessoaQuery buscar(String uuid);
    List<PessoaQuery> encontrarTodasAsPessoa();

}
