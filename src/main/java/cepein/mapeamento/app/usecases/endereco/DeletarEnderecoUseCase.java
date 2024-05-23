package cepein.mapeamento.app.usecases.endereco;

import cepein.mapeamento.app.gateways.EnderecoGateway;
import cepein.mapeamento.utils.clean.application.useCase.UseCaseRequest;

public class DeletarEnderecoUseCase implements UseCaseRequest<Long> {
    private final EnderecoGateway enderecoGateway;
    private Long idEndereco;
    public DeletarEnderecoUseCase(EnderecoGateway enderecoGateway){
        this.enderecoGateway = enderecoGateway;
    }
    @Override
    public void execute(Long id){
        this.idEndereco = id;
        this.verificarExistenciaEndereco();
        this.enderecoGateway.deletar(id);
    }
    private void verificarExistenciaEndereco(){
        this.enderecoGateway.buscar(idEndereco);
    }

}
