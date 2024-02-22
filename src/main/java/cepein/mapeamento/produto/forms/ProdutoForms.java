package cepein.mapeamento.produto.forms;

import cepein.mapeamento.produto.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProdutoForms {

    private String descricao;

    private List<Long> listaDeIdsDasPessoas;

    public Produto converter(Produto produto){
        produto.setDescricao(this.descricao);
        return produto;
    }

}
