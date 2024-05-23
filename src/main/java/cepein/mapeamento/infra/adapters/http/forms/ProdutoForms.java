package cepein.mapeamento.infra.adapters.http.forms;

import cepein.mapeamento.acore.domain.models.produto.ProdutoCommand;
import cepein.mapeamento.acore.domain.models.produto.ProdutoQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoForms {

    private Long id;

    private String descricao;


    public ProdutoCommand converter(ProdutoQuery produtoQuery){
        ProdutoCommand produtoCommand = new ProdutoCommand();
        produtoCommand.setDescricao(Objects.isNull(this.descricao) ? produtoQuery.getDescricao() : this.descricao);
        return produtoCommand;
    }
    public ProdutoCommand converter(){
        ProdutoCommand produtoCommand = new ProdutoCommand();
        produtoCommand.setDescricao(this.descricao);
        return produtoCommand;
    }

}
