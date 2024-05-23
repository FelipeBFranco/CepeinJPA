package cepein.mapeamento.app.services.pessoa;

import cepein.mapeamento.app.gateways.PessoaGatway;

public class VerificarExistenciaPessoa {
    private final PessoaGatway pessoaGatway;

    public VerificarExistenciaPessoa(PessoaGatway pessoaGatway) {
        this.pessoaGatway = pessoaGatway;
    }
    public void verificar(Long id){
        this.pessoaGatway.buscar(id);
    }
    public void verificar(String uuid){
        this.pessoaGatway.buscar(uuid);
    }
}
