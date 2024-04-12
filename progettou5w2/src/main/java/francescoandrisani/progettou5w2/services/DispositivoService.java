package francescoandrisani.progettou5w2.services;
import francescoandrisani.progettou5w2.entities.Dipendente;
import francescoandrisani.progettou5w2.entities.Dispositivo;
import francescoandrisani.progettou5w2.entities.StatoDispositivo;
import francescoandrisani.progettou5w2.exceptions.BadRequestExceptions;
import francescoandrisani.progettou5w2.exceptions.NotFound;
import francescoandrisani.progettou5w2.repositories.DipendenteDAO;
import francescoandrisani.progettou5w2.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {


    @Autowired
    private DispositivoDAO dispositivoDAO;

    @Autowired
    private DipendenteService dipendenteService;


    // GET - LISTA DEI DISPOSITIVI
    public Page<Dispositivo> getUsers(int page, int size, String sortBy){
        if(size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dispositivoDAO.findAll(pageable);
    }

    // GET BY ID - RICERCA DISPOSITIVO TRAMITE ID
    public Dispositivo findById(int id){
        return this.dispositivoDAO.findById(id).orElseThrow(()-> new NotFound(id));
    }

    // POST - INSERIMENTO DI UN NUOVO DISPOSITIVO
    public Dispositivo save(Dispositivo body){
        Dispositivo newDispositivo = new Dispositivo(body.getTipo(),body.getStato());
        return dispositivoDAO.save(newDispositivo);
    }


    // PATCH - ASSEGNAZIONE DI UN DISPOSITIVO AD UN DIPENDENTE
    public Dispositivo assegnaDispositivo(int idDispositivo, int idDipendente) {
        Dispositivo dispositivo = this.findById(idDispositivo);
        Dipendente dipendente = dipendenteService.findById(idDipendente);


        if (!dispositivo.getStato().equals(StatoDispositivo.DISPONIBILE)) {
            throw new BadRequestExceptions("Il dispositivo non è disponibile per l'assegnazione");
        }

        dispositivo.setDipendente(dipendente);
        dispositivo.setStato(StatoDispositivo.ASSEGNATO);

        return this.dispositivoDAO.save(dispositivo);
    }


    // DELETE - ELIMIAZIONE DISPOSITIVO
    public void findByIdAndDelete(int id){
        Dispositivo found = this.findById(id);
        this.dispositivoDAO.delete(found);
    }
}
