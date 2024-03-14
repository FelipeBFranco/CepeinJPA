package exception;

public class PessoaExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public PessoaExistException(String message) {
        super(message);
    }
}
