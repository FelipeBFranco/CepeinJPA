package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaPedidoCommand;
import cepein.mapeamento.app.gateways.PedidoGateway;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.app.gateways.PessoaPedidoGateway;
import cepein.mapeamento.app.services.pessoa.VerificarExistenciaPessoa;
import cepein.mapeamento.infra.adapters.http.forms.PessoaPedidoForms;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

import java.util.ArrayList;
import java.util.List;

public class CadastrarPessoaPedidoUseCase implements UseCaseRequest<PessoaPedidoForms> {
    private final PessoaGatway pessoaGatway;
    private final PedidoGateway pedidoGateway;
    private final PessoaPedidoGateway pessoaPedidoGateway;

    private String uuidPessoa;
    private List<String> uuidPedidoList;
    public CadastrarPessoaPedidoUseCase (PessoaGatway pessoaGatway, PedidoGateway pedidoGateway, PessoaPedidoGateway pessoaPedidoGateway){
        this.pessoaGatway = pessoaGatway;
        this.pedidoGateway = pedidoGateway;
        this.pessoaPedidoGateway = pessoaPedidoGateway;
    }
    public void  execute(PessoaPedidoForms pessoaPedidoForms){

        this.uuidPessoa = pessoaPedidoForms.getUuidPessoa();
        this.uuidPedidoList = pessoaPedidoForms.getUuidPedidoList();

        VerificarExistenciaPessoa verificarExistenciaPessoa = new VerificarExistenciaPessoa(this.pessoaGatway);

        verificarExistenciaPessoa.verificar(this.uuidPessoa);
        this.verificarExistenciaPedido();

        pessoaPedidoGateway.salvar(this.criarListaDePessoaPedido());
    }
    private void verificarExistenciaPedido(){
        this.uuidPedidoList.forEach(this.pedidoGateway::buscar);
    }
    private List<PessoaPedidoCommand> criarListaDePessoaPedido (){
        List<PessoaPedidoCommand> pessoaPedidoCommandList = new ArrayList<>();
        this.uuidPedidoList.forEach(uuidPedido->
                pessoaPedidoCommandList.add(new PessoaPedidoCommand(this.uuidPessoa,uuidPedido)));
        return pessoaPedidoCommandList;
    }
}
