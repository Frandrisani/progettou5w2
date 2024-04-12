package francescoandrisani.progettou5w2.controllers;

import francescoandrisani.progettou5w2.entities.Dispositivo;
import francescoandrisani.progettou5w2.exceptions.BadRequestExceptions;
import francescoandrisani.progettou5w2.payloads.NewRispDTO;
import francescoandrisani.progettou5w2.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    // TUTTI I DISPOSITIVI
    @GetMapping
    public Page<Dispositivo> getAllDispositivi(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "nome") String sortBy) {
        return this.dispositivoService.getUsers(page, size, sortBy);
    }

    // DISPOSITIVO TRAMITE ID
    @GetMapping("/{dispositivoId}")
    public Dispositivo findById(@PathVariable int dispositivoId){
        return this.dispositivoService.findById(dispositivoId);
    }

    // POST NUOVO DISPOSITIVO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewRispDTO saveUser(@RequestBody @Validated Dispositivo body, BindingResult validation){
        if(validation.hasErrors()) {
            throw new BadRequestExceptions(validation.getAllErrors());
        }
        return new NewRispDTO(this.dispositivoService.save(body).getId_Dispositivo());
    }



   /* @PutMapping("/{userId}")
    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody User body){
        return this.usersService.findByIdAndUpdate(userId, body);
    }*/

    // CANCELLA DISPOSITIVO
    @DeleteMapping("/{dispositivoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int dispositivoId){
        this.dispositivoService.findByIdAndDelete(dispositivoId);
    }

  /*  @PostMapping("/upload")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image) throws IOException {

        return this.dispositivoService.(image);

    }*/

}
