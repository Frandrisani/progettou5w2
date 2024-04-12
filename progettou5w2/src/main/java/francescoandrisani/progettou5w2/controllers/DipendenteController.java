package francescoandrisani.progettou5w2.controllers;

import francescoandrisani.progettou5w2.entities.Dipendente;
import francescoandrisani.progettou5w2.exceptions.BadRequestExceptions;
import francescoandrisani.progettou5w2.payloads.NewDipendente;
import francescoandrisani.progettou5w2.payloads.NewEmailDTO;
import francescoandrisani.progettou5w2.payloads.NewRispDTO;
import francescoandrisani.progettou5w2.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // TUTTI I DIPENDENTI
    @GetMapping
    public Page<Dipendente> getAllDispositivi(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "nome") String sortBy) {
        return this.dipendenteService.getUsers(page, size, sortBy);
    }

    // DIPENDENTE TRAMITE ID
    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable int dipendenteId){
        return this.dipendenteService.findById(dipendenteId);
    }

    // POST NUOVO DIPENDENTE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewRispDTO saveUser(@RequestBody @Validated NewDipendente body, BindingResult validation){
        if(validation.hasErrors()) {
            throw new BadRequestExceptions(validation.getAllErrors());
        }
        return new NewRispDTO(this.dipendenteService.save(body).getId_Dipendente());
    }

    // MODIFICA DELL'EMAIL
   @PatchMapping("/changemail/{dipendenteId}")
    public Dipendente findByIdAndUpdateEmail (@PathVariable int dipendenteId, @RequestBody NewEmailDTO body){
        return this.dipendenteService.findByIdAndUpdateMail(dipendenteId, body);
    }


    // CANCELLA DIPENDENTE
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int dipendenteId){
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }


    // MODIFICA DELL'AVATAR
    @PatchMapping("/{id}/upload")
    public Dipendente uploadAvatar(@PathVariable int id,  @RequestParam("avatar")MultipartFile image) throws IOException {
        return this.dipendenteService.updateUrlAvatar(id, image);
    }

}
