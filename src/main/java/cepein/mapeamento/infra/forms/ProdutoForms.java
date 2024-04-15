package cepein.mapeamento.infra.forms;

import cepein.mapeamento.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoForms {

    private String descricao;

    private List<Long> listaDeIdsDasPessoas;

    public Produto converter(Produto produto){
        produto.setDescricao(this.descricao);
        return produto;
    }

}
