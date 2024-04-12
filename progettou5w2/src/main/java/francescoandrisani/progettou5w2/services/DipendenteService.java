package francescoandrisani.progettou5w2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class DipendenteService {

    @Autowired
    private DipendenteDAO dipendenteDAO;

    @Autowired
    private Cloudinary cloudinary;


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


    // PATCH - INSERIMENTO AVATAR SCELTO DAL DIPENDENTE
    public Dipendente updateUrlAvatar(int id, MultipartFile image) throws IOException{
        Dipendente found = this.findById(id);
        String url = (String) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        return found;
    }



    // DELETE - ELIMIAZIONE DIPENDENTE
    public void findByIdAndDelete(int id){
        Dipendente found = this.findById(id);
        this.dipendenteDAO.delete(found);
    }
}
