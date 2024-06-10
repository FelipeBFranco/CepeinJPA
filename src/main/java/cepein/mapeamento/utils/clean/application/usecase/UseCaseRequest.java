package cepein.mapeamento.utils.clean.application.usecase;

public interface UseCaseRequest<REQUEST>{
    void execute(REQUEST request);
}
