package francescoandrisani.progettou5w2.services;

import francescoandrisani.progettou5w2.entities.User;
import francescoandrisani.progettou5w2.exceptions.NotFound;
import francescoandrisani.progettou5w2.repositories.UsersDAO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersDAO usersDAO;

    public Page<User> getUsers(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.usersDAO.findAll(pageable);
    }

    public User save(User body){
        // 1. Verifico se l'email è già in uso
        this.usersDAO.findByEmail(body.getEmail()).ifPresent(
                // 2. Se lo è triggero un errore
                user -> {
                    try {
                        throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        // 3. Creo un nuovo oggetto User con i dati provenienti dal body
        User newUser = new User(body.getName(), body.getSurname(), body.getEmail(), body.getPassword(),
                "https://ui-avatars.com/api/?name="+ body.getName() + "+" + body.getSurname());

        // 4. Salvo lo user
        return usersDAO.save(newUser);
    }

    public User findById(UUID userId){
        return this.usersDAO.findById(userId).orElseThrow(() -> new NotFound(userId));
    }

    public User findByIdAndUpdate(UUID userId, User modifiedUser){
        User found = this.findById(userId);
        found.setName(modifiedUser.getName());
        found.setSurname(modifiedUser.getSurname());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());
        found.setAvatarURL("https://ui-avatars.com/api/?name="+ modifiedUser.getName() + "+" + modifiedUser.getSurname());
        return this.usersDAO.save(found);
    }

    public void findByIdAndDelete(UUID userId){
        User found = this.findById(userId);
        this.usersDAO.delete(found);
    }

    public User findByEmail(String email) throws Exception {
        return usersDAO.findByEmail(email).orElseThrow(Exception::new);
    }

}
