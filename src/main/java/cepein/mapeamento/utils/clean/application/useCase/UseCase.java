package cepein.mapeamento.utils.clean.application.useCase;

public interface UseCase<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request);
}
