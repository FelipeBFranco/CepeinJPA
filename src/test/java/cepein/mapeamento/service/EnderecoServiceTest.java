package cepein.mapeamento.service;

import cepein.mapeamento.infra.dto.EnderecoDto;
import cepein.mapeamento.infra.forms.EnderecoForms;
import cepein.mapeamento.infra.service.EnderecoService;
import cepein.mapeamento.model.Endereco;
import cepein.mapeamento.infra.repository.EnderecoRepository;
import cepein.mapeamento.infra.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.model.Pessoa;
import exception.ObjectNotFoundException;
import exception.PessoaExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

//teste integrado
//teste apis
class EnderecoServiceTest {
    public static final Long ID = 15L;
    public static final String UUID = "cku1vufhjv";
    public static final String RUA = "Rua dos Loucos";
    public static final String CEP = "19800-001";
    public static final String CIDADE = "Assis";
    public static final String ESTADO = "SP";
    public static final Long ID_PESSOA = 1L;
    public static final String UUID_PESSOA = "khvv2jkv";
    public static final String ENDERECO_NAO_ENCONTRADO = "Endereço não encontrado";
    public static final int INDEX = 0;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;
    private Endereco endereco;
    private EnderecoForms enderecoForms;
    private EnderecoDto enderecoDto;
    private Pessoa pessoa;
    private PessoaDtoParaRelacionamento pessoaDtoParaRelacionamento;

    private Optional<Endereco> enderecoOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startEndereco();
    }

    @Test
    void listarEnderecos() {
        Mockito.when(enderecoRepository.findAll()).thenReturn(List.of(endereco));
        List<EnderecoDto> response = enderecoService.listarEnderecos();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals(EnderecoDto.class, response.get(INDEX).getClass());

        Assertions.assertEquals(enderecoDto.getIdEndereco(), response.get(INDEX).getIdEndereco());
        Assertions.assertEquals(enderecoDto.getUuid(), response.get(INDEX).getUuid());
        Assertions.assertEquals(enderecoDto.getRua(), response.get(INDEX).getRua());
        Assertions.assertEquals(enderecoDto.getCep(), response.get(INDEX).getCep());
        Assertions.assertEquals(enderecoDto.getCidade(), response.get(INDEX).getCidade());
        Assertions.assertEquals(enderecoDto.getEstado(), response.get(INDEX).getEstado());
        Assertions.assertEquals(enderecoDto.getPessoaPorId().getId(), response.get(INDEX).getPessoaPorId().getId());
        Assertions.assertEquals(enderecoDto.getPessoaPorUuid().getUuid(), response.get(INDEX).getPessoaPorUuid().getUuid());

    }
    @Test
    void buscarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);

        Endereco response = enderecoService.buscarEndereco(ID);


        Assertions.assertEquals(Endereco.class, response.getClass());
        Assertions.assertEquals(ID, response.getId_endereco());
        Assertions.assertEquals(UUID, response.getUuid());
        Assertions.assertEquals(RUA, response.getRua());
        Assertions.assertEquals(CEP, response.getCep());
        Assertions.assertEquals(CIDADE, response.getCidade());
        Assertions.assertEquals(ESTADO, response.getEstado());
        Assertions.assertEquals(pessoa.getId(), response.getPessoaPorId().getId());
        Assertions.assertEquals(pessoa.getUuid(), response.getPessoaPorUuid().getUuid());
    }
    @Test
    void buscarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> enderecoService.buscarEndereco(ID));
        Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    @Test
    void procurarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);

        EnderecoDto response = enderecoService.procurarEndereco(ID);

        Assertions.assertEquals(EnderecoDto.class, response.getClass());
        Assertions.assertEquals(enderecoDto.getIdEndereco(), response.getIdEndereco());
        Assertions.assertEquals(enderecoDto.getUuid(), response.getUuid());
        Assertions.assertEquals(enderecoDto.getRua(), response.getRua());
        Assertions.assertEquals(enderecoDto.getCep(), response.getCep());
        Assertions.assertEquals(enderecoDto.getCidade(), response.getCidade());
        Assertions.assertEquals(enderecoDto.getEstado(), response.getEstado());
        Assertions.assertEquals(enderecoDto.getPessoaPorId().getId(), response.getPessoaPorId().getId());
        Assertions.assertEquals(enderecoDto.getPessoaPorUuid().getUuid(), response.getPessoaPorUuid().getUuid());
    }
    @Test
    void procurarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> enderecoService.procurarEndereco(ID));
        Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    @Test
    void cadastrarEndereco() {

        enderecoService.cadastrarEndereco(this.enderecoForms);
        Mockito.verify(enderecoRepository,Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void alterarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);
        Endereco enderocoAlterado = enderecoService.buscarEndereco(ID);

        enderecoService.alterarEndereco(ID,this.enderecoForms);

        Mockito.verify(enderecoRepository,Mockito.times(1)).save(Mockito.any());

        Assertions.assertEquals(enderecoForms.getUuid(), enderocoAlterado.getUuid());
        Assertions.assertEquals(enderecoForms.getRua(), enderocoAlterado.getRua());
        Assertions.assertEquals(enderecoForms.getCep(), enderocoAlterado.getCep());
        Assertions.assertEquals(enderecoForms.getCidade(), enderocoAlterado.getCidade());
        Assertions.assertEquals(enderecoForms.getEstado(), enderocoAlterado.getEstado());
    }
    @Test
    void alterarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> enderecoService.alterarEndereco(ID,this.enderecoForms));
        Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }

    @Test
    void deletarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Endereco()));
        Mockito.doNothing().when(enderecoRepository).deleteById(Mockito.anyLong());

        enderecoService.deletarEndereco(ID);

        Mockito.verify(enderecoRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    void deletarEnderecoExeptionPessoaExist() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);
        PessoaExistException pessoaExistException = Assertions
                .assertThrows(PessoaExistException.class, () -> enderecoService.deletarEndereco(ID));
        Assertions.assertEquals("Uma pessoa possui este endereço, não é possivel deleta-lo",pessoaExistException.getMessage());

    }
    @Test
    void deletarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));
        ObjectNotFoundException objectNotFoundException = Assertions
                .assertThrows(ObjectNotFoundException.class, () -> enderecoService.deletarEndereco(ID));
        Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO,objectNotFoundException.getMessage());

    }
    private void startEndereco(){
        this.pessoa = new Pessoa();
        pessoa.setId(ID_PESSOA);
        pessoa.setUuid(UUID_PESSOA);
        this.pessoaDtoParaRelacionamento = new PessoaDtoParaRelacionamento(pessoa);
        this.endereco = new Endereco(ID, UUID, RUA, CEP, CIDADE, ESTADO,pessoa,pessoa);
        this.enderecoDto = new EnderecoDto(ID,UUID,RUA,CEP,CIDADE,ESTADO,pessoaDtoParaRelacionamento,pessoaDtoParaRelacionamento);
        this.enderecoOptional = Optional.of( new Endereco(ID, UUID, RUA, CEP, CIDADE, ESTADO,pessoa,pessoa));
        this.enderecoForms = new EnderecoForms(UUID, "Avenida Brasil", CEP, "Frutal do Campo", "liugkuyfuf");
    }
}