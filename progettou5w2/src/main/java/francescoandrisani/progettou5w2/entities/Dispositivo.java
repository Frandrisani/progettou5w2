package francescoandrisani.progettou5w2.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Dispositivo {
    //Attributi
    @Id
    @GeneratedValue
    private int id_Dispositivo;

    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipo;
    @Enumerated(EnumType.STRING)
    private  StatoDispositivo stato;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    // Costruttore
    public Dispositivo(TipoDispositivo tipo, StatoDispositivo stato, Dipendente dipendente){
        this.tipo= tipo;
        this.stato= stato;
        this.dipendente=dipendente;
    }

}
