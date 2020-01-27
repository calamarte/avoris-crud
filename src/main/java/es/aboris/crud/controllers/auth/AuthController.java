package es.aboris.crud.controllers.auth;

import es.aboris.crud.model.User;
import es.aboris.crud.repositories.UserRepository;
import es.aboris.crud.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/auth")
    public User login(
            @RequestParam("username") String username,
            @RequestParam("password") String pass
    ){

        Optional<es.aboris.crud.model.User> result = repository.findByUsername(username);

        if(result.isPresent() && result.get().getPassword().equals(pass)){
            return new User(username, JWTUtils.getToken(username));
        }

        return null;
    }


    private class User{
        private String name;
        private String token;

        public User(String name, String token) {
            this.name = name;
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
