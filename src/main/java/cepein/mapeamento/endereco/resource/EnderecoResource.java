package cepein.mapeamento.endereco.resource;

import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.forms.EnderecoForms;
import cepein.mapeamento.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(this.enderecoService.listarEnderecos());
    }

    @GetMapping("procurar-por-id/{idEndereco}")
    public ResponseEntity<EnderecoDto> listarEnderecoPorId(@PathVariable Long idEndereco){
        return ResponseEntity.ok(this.enderecoService.procurarEndereco(idEndereco));
    }
    @PostMapping("cadastrar-endereco")
    public ResponseEntity<HttpStatus> cadastraEndereco(@RequestBody EnderecoForms enderecoForms){
        this.enderecoService.cadastrarEndereco(enderecoForms);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping ("alterar-endereco/{idEndereco}")
    public ResponseEntity<HttpStatus> alterarEndereco(@PathVariable Long idEndereco,@RequestBody EnderecoForms enderecoForms){
        this.enderecoService.alterarEndereco(idEndereco,enderecoForms);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("deletar-endereco/{idEndereco}")
    public ResponseEntity<HttpStatus> deleterEndereco(@PathVariable Long idEndereco){
        this.enderecoService.deletarEndereco(idEndereco);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
