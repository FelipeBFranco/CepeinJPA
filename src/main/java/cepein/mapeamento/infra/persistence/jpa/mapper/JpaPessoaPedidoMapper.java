package cepein.mapeamento.infra.persistence.jpa.mapper;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaPedidoCommand;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaPedido.JpaPessoaPedidoIdEntity;

public class JpaPessoaPedidoMapper {
    public static JpaPessoaPedidoCommandEntity toEntity(PessoaPedidoCommand pessoaPedidoCommand){
        return new JpaPessoaPedidoCommandEntity(
                new JpaPessoaPedidoIdEntity(pessoaPedidoCommand.getUuidPessoa(), pessoaPedidoCommand.getUuidPedido()));
    }
}
