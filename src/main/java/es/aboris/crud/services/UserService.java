package es.aboris.crud.services;

import es.aboris.crud.model.User;
import es.aboris.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, Integer> {

    @Autowired
    protected UserService(UserRepository repository) {
        super(repository, User.class);
    }

    public Optional<User> findByUsername(String username){
        return ((UserRepository)repository).findByUsername(username);
    }

}
