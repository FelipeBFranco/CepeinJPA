package cepein.mapeamento.endereco.resource;

import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.forms.EnderecoForms;
import cepein.mapeamento.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("listar-por-id/{idEndereco}")
    public ResponseEntity<EnderecoDto> listarEnderecoPorId(@PathVariable Long idEndereco){
        return this.enderecoService.procurarEndereco(idEndereco);
    }
    @PostMapping("cadastrar-endereco")
    public ResponseEntity<Void> cadastraEndereco(@RequestBody EnderecoForms enderecoForms){
        return this.enderecoService.cadastrarEndereco(enderecoForms);
    }
    @PutMapping ("alterar-endereco/{idEndereco}")
    public ResponseEntity<Void> alterarEndereco(@PathVariable Long idEndereco,@RequestBody EnderecoForms enderecoForms){
        return this.enderecoService.alterarEndereco(idEndereco,enderecoForms);
    }

    @DeleteMapping("deletar-endereco/{idEndereco}")
    public ResponseEntity<Void> deleterEndereco(@PathVariable Long idEndereco){
        return this.enderecoService.deletarEndereco(idEndereco);
    }
}
