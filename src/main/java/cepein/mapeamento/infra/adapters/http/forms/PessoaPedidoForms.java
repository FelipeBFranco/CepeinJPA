package cepein.mapeamento.infra.adapters.http.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
@Getter
@AllArgsConstructor
public class PessoaPedidoForms {

    private String uuidPessoa;
    private List<String> uuidPedidoList;
}
