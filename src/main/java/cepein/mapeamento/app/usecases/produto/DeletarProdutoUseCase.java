package cepein.mapeamento.app.usecases.produto;

import cepein.mapeamento.app.gateways.ProdutoGateway;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class DeletarProdutoUseCase implements UseCaseRequest<Long> {
    private final ProdutoGateway produtoGateway;
    private Long idProduto;
    public DeletarProdutoUseCase(ProdutoGateway produtoGateway){
        this.produtoGateway = produtoGateway;
    }
    @Override
    public void execute(Long id){
        this.idProduto = id;
        this.verificarExistenciaPedido();
        this.produtoGateway.deletar(id);
    }
    private void verificarExistenciaPedido(){
        this.produtoGateway.buscar(idProduto);
    }
}
