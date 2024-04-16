package francescoandrisani.progettou5w2.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "dispositivi")
@Setter
@Getter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"role", "authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "enabled"})
public class Dispositivo implements UserDetails {
    //Attributi
    @Id
    @GeneratedValue
    private int id_Dispositivo;

    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipo;
    @Enumerated(EnumType.STRING)
    private  StatoDispositivo stato;
    @Enumerated(EnumType.STRING)
    private Ruoli ruoli;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    // Costruttore
    public Dispositivo(TipoDispositivo tipo, StatoDispositivo stato){
        this.tipo= tipo;
        this.stato= stato;
        this.ruoli= Ruoli.ADMIN;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruoli.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.id_Dispositivo);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
