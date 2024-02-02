package cepein.mapeamento.endereco.resource;

import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
    private final EnderecoService enderecoService;
    @Autowired
    public EnderecoResource(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("listar")
    public ResponseEntity<List<EnderecoDto>> listarEnderecos(){
        return this.enderecoService.listarEnderecos();
    }
}
