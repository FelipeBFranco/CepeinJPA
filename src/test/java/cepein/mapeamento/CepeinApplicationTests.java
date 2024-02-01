package cepein.mapeamento;


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
        pessoa.setNomePessoa("MarcolaDoBCC");

        PessoaRepository mockRepositorio = Mockito.mock(PessoaRepository.class);


        Mockito.when(mockRepositorio.save(pessoa)).thenReturn(pessoa);


        PessoaService service = new PessoaService();

    }



}

