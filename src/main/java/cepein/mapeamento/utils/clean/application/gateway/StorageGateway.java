package cepein.mapeamento.utils.clean.application.gateway;

import cepein.mapeamento.utils.clean.application.gateway.CommandGateway;
import cepein.mapeamento.utils.clean.application.gateway.QueryGateway;

public interface StorageGateway<COMMANDMODEL,QUERYMODEL,ID> extends CommandGateway<COMMANDMODEL,ID>, QueryGateway<QUERYMODEL,ID> {

}
