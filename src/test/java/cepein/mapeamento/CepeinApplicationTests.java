package cepein.mapeamento;


import cepein.mapeamento.pessoa.model.Pessoa;
import cepein.mapeamento.pessoa.repository.PessoaRepository;
import cepein.mapeamento.pessoa.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CepeinApplicationTests {

    @Test
    public void deveSalvarPessoa() {

        // Cria uma pessoa
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("MarcolaDoBCC");

        PessoaRepository mockRepositorio = Mockito.mock(PessoaRepository.class);


        Mockito.when(mockRepositorio.save(pessoa)).thenReturn(pessoa);


        PessoaService service = new PessoaService(mockRepositorio);

        Pessoa pessoaSalva = service.salvar(pessoa);

        //Assertions.assertNull(pessoaSalva);
    }


}

