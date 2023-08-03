package mk.ukim.finki.backend.model.Exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String text) {
        super(text);
    }
}
