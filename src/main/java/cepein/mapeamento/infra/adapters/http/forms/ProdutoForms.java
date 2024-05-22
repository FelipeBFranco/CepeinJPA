package cepein.mapeamento.infra.adapters.http.forms;

import cepein.mapeamento.acore.domain.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoForms {

    private Long id;

    private String descricao;

    private List<Long> listaDeIdsDasPessoas;

    public Produto converter(Produto produto){
        produto.setDescricao(this.descricao);
        return produto;
    }
    public Produto converter(){
        Produto produto = new Produto();
        produto.setDescricao(this.descricao);
        return produto;
    }

}
