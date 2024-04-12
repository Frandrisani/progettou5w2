package francescoandrisani.progettou5w2.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Dipendente {
    // Attributi
    @Id
    @GeneratedValue
    private int id_Dipendente;

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivi = new ArrayList<>();

    // Costruttore
    public Dipendente(String username, String nome, String cognome, String email, String avatar){
        this.username= username;
        this.nome= nome;
        this.cognome=cognome;
        this.email=email;
        this.avatar=avatar;
    }
}
