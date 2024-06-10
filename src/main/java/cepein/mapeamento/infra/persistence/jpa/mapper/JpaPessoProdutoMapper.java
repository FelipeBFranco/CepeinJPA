package cepein.mapeamento.infra.persistence.jpa.mapper;


import cepein.mapeamento.acore.domain.models.pessoa.PessoaProdutoCommand;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoCommandEntity;
import cepein.mapeamento.infra.persistence.jpa.entities.pessoa.pessoaProduto.JpaPessoaProdutoIdEntity;

public class JpaPessoProdutoMapper {
    public static JpaPessoaProdutoCommandEntity toEntity(PessoaProdutoCommand pessoaProdutoCommand){
        return new JpaPessoaProdutoCommandEntity(
                new JpaPessoaProdutoIdEntity(pessoaProdutoCommand.getIdPessoa(), pessoaProdutoCommand.getIdProduto()));
    }
}
