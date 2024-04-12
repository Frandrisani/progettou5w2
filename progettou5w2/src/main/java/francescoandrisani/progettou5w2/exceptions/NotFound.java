package francescoandrisani.progettou5w2.exceptions;
public class NotFound extends RuntimeException {
    public NotFound(int id) {
        super("Elemento con id " + id + " non trovato");
    }
}
