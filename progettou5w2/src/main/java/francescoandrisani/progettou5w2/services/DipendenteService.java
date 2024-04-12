package francescoandrisani.progettou5w2.services;

import francescoandrisani.progettou5w2.entities.Dipendente;
import francescoandrisani.progettou5w2.exceptions.BadRequestExceptions;
import francescoandrisani.progettou5w2.exceptions.NotFound;
import francescoandrisani.progettou5w2.payloads.NewDipendente;
import francescoandrisani.progettou5w2.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DipendenteService {

    @Autowired
    private DipendenteDAO dipendenteDAO;


    // GET - LISTA DEI DIPENDENTI
    public Page<Dipendente> getUsers(int page, int size, String sortBy){
        if(size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteDAO.findAll(pageable);
    }

    // GET BY ID - RICERCA DIPENDENTE TRAMITE ID
    public Dipendente findById(int id){
       return this.dipendenteDAO.findById(id).orElseThrow(()-> new NotFound(id));
    }

    // POST - INSERIMENTO DI UN NUOVO DIPENDENTE
    public Dipendente save(NewDipendente body){
        this.dipendenteDAO.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestExceptions("L'email " + dipendente.getEmail() + " è già in uso!");
                }
        );
        Dipendente newDipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email(),
                "https://ui-avatars.com/api/?name="+ body.nome() + "+" + body.cognome());
        return dipendenteDAO.save(newDipendente);
    }

    // PATCH - MODIFICA EMAIL DEL DIPENDENTE
    public Dipendente findByIdAndUpdateMail(int id, Dipendente modifiedDipendente){
        Dipendente found = this.findById(id);
        found.setEmail(modifiedDipendente.getEmail());
        return this.dipendenteDAO.save(found);
    }


    // PATCH - INSERIMENTO AVATAR SCELTO DALL'UTENTE



    // DELETE - ELIMIAZIONE UTENTE
    public void findByIdAndDelete(int id){
        Dipendente found = this.findById(id);
        this.dipendenteDAO.delete(found);
    }
}
