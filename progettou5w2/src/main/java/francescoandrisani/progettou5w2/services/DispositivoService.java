package francescoandrisani.progettou5w2.services;
import francescoandrisani.progettou5w2.entities.Dispositivo;
import francescoandrisani.progettou5w2.exceptions.NotFound;
import francescoandrisani.progettou5w2.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DispositivoService {


    @Autowired
    private DispositivoDAO dispositivoDAO;


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


    // PATCH - MODIFICA STATO DEL DISPOSITIVO
    /*public Dispositivo findByIdAndUpdateStato(int id, Dispositivo modifiedDipendente){
        Dispositivo found = this.findById(id);

    }*/




    // DELETE - ELIMIAZIONE DISPOSITIVO
    public void findByIdAndDelete(int id){
        Dispositivo found = this.findById(id);
        this.dispositivoDAO.delete(found);
    }
}
