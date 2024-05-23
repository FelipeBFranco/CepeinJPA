package cepein.mapeamento.utils.clean.application.useCase;

public interface UseCaseRequest<REQUEST>{
    void execute(REQUEST request);
}
