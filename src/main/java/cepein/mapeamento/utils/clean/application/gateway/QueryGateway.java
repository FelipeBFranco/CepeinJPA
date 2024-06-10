package cepein.mapeamento.utils.clean.application.gateway;

public interface QueryGateway <MODEL,ID>{
    MODEL buscar(ID id);
}
