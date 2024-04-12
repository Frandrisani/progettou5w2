package francescoandrisani.progettou5w2.payloads;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime timeError) {
}
