package cepein.mapeamento.endereco.service;

import cepein.mapeamento.endereco.dto.EnderecoDto;
import cepein.mapeamento.endereco.forms.EnderecoForms;
import cepein.mapeamento.endereco.model.Endereco;
import cepein.mapeamento.endereco.repository.EnderecoRepository;
import cepein.mapeamento.pessoa.dto.PessoaDtoParaRelacionamento;
import cepein.mapeamento.pessoa.model.Pessoa;
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


class EnderecoServiceTest {
    public static final Long ID_ENDERECO = 15L;
    public static final String UUID = "cku1vufhjv";
    public static final String RUA = "Rua dos Loucos";
    public static final String CEP = "19800-001";
    public static final String CIDADE = "Assis";
    public static final String ESTADO = "SP";
    public static final Pessoa PESSOA = new Pessoa();
    public static final PessoaDtoParaRelacionamento PESSOA_DTO_PARA_RELACIONAMENTO = new PessoaDtoParaRelacionamento(PESSOA);
    public static final String ENDERECO_NAO_ENCONTRADO = "Endereço não encontrado";
    public static final int INDEX = 0;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    private PessoaDtoParaRelacionamento pessoaDtoParaRelacionamento;
    private Endereco endereco;
    private EnderecoForms enderecoForms;
    private EnderecoDto enderecoDto;

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

        Assertions.assertEquals(ID_ENDERECO, response.get(INDEX).getIdEndereco());
        Assertions.assertEquals(UUID, response.get(INDEX).getUuid());
        Assertions.assertEquals(RUA, response.get(INDEX).getRua());
        Assertions.assertEquals(CEP, response.get(INDEX).getCep());
        Assertions.assertEquals(CIDADE, response.get(INDEX).getCidade());
        Assertions.assertEquals(ESTADO, response.get(INDEX).getEstado());

    }
    @Test
    void buscarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);

        Endereco response = enderecoService.buscarEndereco(ID_ENDERECO);

        Assertions.assertEquals(Endereco.class, response.getClass());
        Assertions.assertEquals(ID_ENDERECO, response.getId_endereco());
        Assertions.assertEquals(UUID, response.getUuid());
        Assertions.assertEquals(RUA, response.getRua());
        Assertions.assertEquals(CEP, response.getCep());
        Assertions.assertEquals(CIDADE, response.getCidade());
        Assertions.assertEquals(ESTADO, response.getEstado());
        Assertions.assertEquals(PESSOA, response.getPessoaPorId());
        Assertions.assertEquals(PESSOA, response.getPessoaPorUuid());
    }
    @Test
    void buscarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));
        try {
            enderecoService.buscarEndereco(ID_ENDERECO);
        }catch (Exception exception){
            Assertions.assertEquals(ObjectNotFoundException.class, exception.getClass());
            Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO, exception.getMessage());
        }
    }
    @Test
    void procurarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);

        EnderecoDto response = enderecoService.procurarEndereco(ID_ENDERECO);

        Assertions.assertEquals(EnderecoDto.class, response.getClass());
        Assertions.assertEquals(UUID, response.getUuid());
        Assertions.assertEquals(RUA, response.getRua());
        Assertions.assertEquals(CEP, response.getCep());
        Assertions.assertEquals(CIDADE, response.getCidade());
        Assertions.assertEquals(ESTADO, response.getEstado());
    }

    @Test
    void cadastrarEndereco() {

        Assertions.assertNull(enderecoService.cadastrarEndereco(this.enderecoForms));
        Mockito.verify(enderecoRepository,Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void alterarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);
        Endereco enderocoAlterado = enderecoService.buscarEndereco(ID_ENDERECO);
        
        Assertions.assertNull(enderecoService.alterarEndereco(ID_ENDERECO,this.enderecoForms));
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
        try {
            enderecoService.alterarEndereco(ID_ENDERECO,this.enderecoForms);
        }catch (Exception exception){
            Assertions.assertEquals(ObjectNotFoundException.class, exception.getClass());
            Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO, exception.getMessage());
        }
    }

    @Test
    void deletarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Endereco()));
        Mockito.doNothing().when(enderecoRepository).deleteById(Mockito.anyLong());

        Assertions.assertNull(enderecoService.deletarEndereco(ID_ENDERECO));
        Mockito.verify(enderecoRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    void deletarEnderecoExeptionPessoaExist() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(enderecoOptional);

        try {
            enderecoService.deletarEndereco(ID_ENDERECO);
        }catch (Exception exception){
            Assertions.assertEquals(PessoaExistException.class, exception.getClass());
            Assertions.assertEquals("Uma pessoa possui este endereço, não é possivel deleta-lo", exception.getMessage());
        }
    }
    @Test
    void deletarEnderecoExeptionObjectNotFound() {
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(ENDERECO_NAO_ENCONTRADO));

        try {
            enderecoService.deletarEndereco(ID_ENDERECO);
        }catch (Exception exception){
            Assertions.assertEquals(ObjectNotFoundException.class, exception.getClass());
            Assertions.assertEquals(ENDERECO_NAO_ENCONTRADO, exception.getMessage());
        }
    }
    private void startEndereco(){
        this.endereco = new Endereco(ID_ENDERECO, UUID, RUA, CEP, CIDADE, ESTADO,PESSOA,PESSOA);
        this.enderecoDto = new EnderecoDto(ID_ENDERECO,UUID,RUA,CEP,CIDADE,ESTADO,PESSOA_DTO_PARA_RELACIONAMENTO,PESSOA_DTO_PARA_RELACIONAMENTO);
        this.enderecoOptional = Optional.of( new Endereco(ID_ENDERECO, UUID, RUA, CEP, CIDADE, ESTADO,PESSOA,PESSOA));
        this.enderecoForms = new EnderecoForms(UUID, "Avenida Brasil", CEP, "Frutal do Campo", ESTADO);
    }
}