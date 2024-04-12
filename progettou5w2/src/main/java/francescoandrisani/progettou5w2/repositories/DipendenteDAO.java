package francescoandrisani.progettou5w2.repositories;

import francescoandrisani.progettou5w2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteDAO extends JpaRepository<Dipendente, Integer> {
}
