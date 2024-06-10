package cepein.mapeamento.app.usecases.pessoa;

import cepein.mapeamento.acore.domain.models.pessoa.PessoaProdutoCommand;
import cepein.mapeamento.app.gateways.PessoaGatway;
import cepein.mapeamento.app.gateways.PessoaProdutoGateway;
import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.app.services.pessoa.VerificarExistenciaPessoa;
import cepein.mapeamento.infra.adapters.http.forms.PessoaProdutoForms;
import cepein.mapeamento.utils.clean.application.usecase.UseCaseRequest;

import java.util.ArrayList;
import java.util.List;

public class CadastrarPessoaProdutoUseCase implements UseCaseRequest<PessoaProdutoForms> {
    private final PessoaGatway pessoaGatway;
    private final ProdutoGateway produtoGateway;
    private final PessoaProdutoGateway pessoaProdutoGateway;

    private Long idPessoa;
    private List<Long> idProdutoList;
    public CadastrarPessoaProdutoUseCase (PessoaGatway pessoaGatway, ProdutoGateway produtoGateway, PessoaProdutoGateway pessoaProdutoGateway){
        this.pessoaGatway = pessoaGatway;
        this.produtoGateway = produtoGateway;
        this.pessoaProdutoGateway = pessoaProdutoGateway;
    }
    public void  execute(PessoaProdutoForms pessoaProdutoForms){
        this.idPessoa = pessoaProdutoForms.getIdPessoa();
        this.idProdutoList = pessoaProdutoForms.getIdProdutoList();

        VerificarExistenciaPessoa verificarExistenciaPessoa = new VerificarExistenciaPessoa(this.pessoaGatway);

        verificarExistenciaPessoa.verificar(this.idPessoa);
        this.verificarExistenciaProduto();
        List<PessoaProdutoCommand> pessoaProdutoCommandList = this.criarListaDePessoaProduto();
        pessoaProdutoGateway.salvar(pessoaProdutoCommandList);
    }
    private void verificarExistenciaProduto(){
        this.idProdutoList.forEach(this.produtoGateway::buscar);
    }
    private List<PessoaProdutoCommand> criarListaDePessoaProduto (){
        List<PessoaProdutoCommand> pessoaProdutoCommandList = new ArrayList<>();
        this.idProdutoList.forEach(idProduto-> pessoaProdutoCommandList.add(new PessoaProdutoCommand(this.idPessoa,idProduto)));
        return pessoaProdutoCommandList;
    }
}
