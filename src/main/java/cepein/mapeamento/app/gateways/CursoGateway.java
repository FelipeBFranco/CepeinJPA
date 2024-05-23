package cepein.mapeamento.app.gateways;


import cepein.mapeamento.acore.domain.models.curso.CursoCommand;
import cepein.mapeamento.acore.domain.models.curso.CursoQuery;
import cepein.mapeamento.utils.clean.application.gateway.StorageGateway;

import java.util.List;

public interface CursoGateway extends StorageGateway<CursoCommand,CursoQuery,Long>{
    List<CursoQuery> encontrarTodosOsCursos();

}
