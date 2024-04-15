package francescoandrisani.progettou5w2.services;

import francescoandrisani.progettou5w2.entities.User;
import francescoandrisani.progettou5w2.exceptions.UnauthorizedException;
import francescoandrisani.progettou5w2.payloads.AdminLoginDTO;
import francescoandrisani.progettou5w2.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private  UsersService usersService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(AdminLoginDTO payload) throws Exception {
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite l'email l'utente
        User user = this.usersService.findByEmail(payload.email());
        // 1.2 Verifico se la password combacia con quella ricevuta nel payload
        if(user.getPassword().equals(payload.password())) {
            // 2. Se è tutto OK, genero un token e lo torno
            return jwtTools.createToken(user);
        } else {
            // 3. Se le credenziali invece non fossero OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}