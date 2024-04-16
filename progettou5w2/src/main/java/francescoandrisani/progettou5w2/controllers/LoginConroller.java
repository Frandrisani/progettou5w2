package francescoandrisani.progettou5w2.controllers;

import francescoandrisani.progettou5w2.entities.User;
import francescoandrisani.progettou5w2.exceptions.BadRequestExceptions;
import francescoandrisani.progettou5w2.payloads.AdminLoginDTO;
import francescoandrisani.progettou5w2.payloads.NewRispDTO;
import francescoandrisani.progettou5w2.services.AuthService;
import francescoandrisani.progettou5w2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginConroller {
    @Autowired
    private AuthService authService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public AdminLoginDTO login(@RequestBody AdminLoginDTO payload) throws Exception {
        return new AdminLoginDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewRispDTO saveUser(@RequestBody @Validated User body, BindingResult validation){
        if(validation.hasErrors()) {
            throw new BadRequestExceptions(validation.getAllErrors());
        }
        return new NewRispDTO(this.usersService.save(body).getId());
    }
}
