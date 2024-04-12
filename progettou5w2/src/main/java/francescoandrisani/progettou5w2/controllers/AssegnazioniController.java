package francescoandrisani.progettou5w2.controllers;
import francescoandrisani.progettou5w2.entities.Dispositivo;
import francescoandrisani.progettou5w2.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// SPIEGO COME UNZIONA IL MECCANISMO DELLA STRUTTURA DI QUESTO ENDPOINT:
//Inviando una richiesta PATCH all'endpoint
// /assegnazioni/{idDispositivo}/{idDipendente}
// verrà assegnato il dispositivo con ID idDispositivo al dipendente con ID idDipendente
// e il suo stato verrà cambiato in "ASSEGNATO"


@RestController
@RequestMapping("/assegnazioni")
public class AssegnazioniController {

    @Autowired
    private DispositivoService dispositivoService;

    // PATCH - ASSEGNAZIONE DI UN DISPOSITIVO AD UN UTENTE
    @PatchMapping("/{idDispositivo}/{idDipendente}")
    @ResponseStatus(HttpStatus.OK)
    public Dispositivo assegnaDispositivo(@PathVariable int idDispositivo, @PathVariable int idDipendente) {
        return dispositivoService.assegnaDispositivo(idDispositivo, idDipendente);
    }
}
