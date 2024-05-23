package cepein.mapeamento.infra.adapters.http.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class PessoaProdutoForms {
    private Long idPessoa;
    private List<Long> idProdutoList;
}
