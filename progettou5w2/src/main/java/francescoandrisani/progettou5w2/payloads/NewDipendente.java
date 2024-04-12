package francescoandrisani.progettou5w2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendente(
        @NotEmpty(message = "ATTENZIONE! L'username è obbligatorio")
        @Size(min = 5, max = 20, message = "L'username deve essere compreso tra i 5 e i 20 caratteri")
        String username,
        @NotEmpty(message = "ATTENZIONE! Il nome è obbligatorio")
        @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra i 2 e i 30 caratteri")
        String nome,
        @NotEmpty(message = "ATTENZIONE! Il cognome è obbligatorio")
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra i 2 e i 30 caratteri")
        String cognome,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email
        ) {
}
