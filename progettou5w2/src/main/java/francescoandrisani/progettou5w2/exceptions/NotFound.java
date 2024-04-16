package francescoandrisani.progettou5w2.exceptions;

import java.util.UUID;

public class NotFound extends RuntimeException {
    public NotFound(UUID id) {
        super("Elemento con id " + id + " non trovato");
    }
}
