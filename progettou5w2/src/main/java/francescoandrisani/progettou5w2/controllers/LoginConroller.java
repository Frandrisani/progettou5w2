package francescoandrisani.progettou5w2.controllers;

import francescoandrisani.progettou5w2.entities.User;
import francescoandrisani.progettou5w2.payloads.AdminLoginDTO;
import francescoandrisani.progettou5w2.payloads.AdminLoginResponseDTO;
import francescoandrisani.progettou5w2.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginConroller {

    @Autowired
    private AuthService authService;

    @Autowired
    private User user;

    public AdminLoginDTO login(@RequestBody AdminLoginDTO payload){
        return new AdminLoginResponseDTO(this.)
    }
}
