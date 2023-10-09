package com.iftm.newsletter.services;

import com.iftm.newsletter.models.user.User;
import com.iftm.newsletter.models.user.UserDTO;
import com.iftm.newsletter.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public ResponseEntity<User> save(UserDTO user) {
        String passEncoder = new BCryptPasswordEncoder().encode(user.password());
        var userDb = new User(user.login().toUpperCase(), passEncoder, user.role());
        if (userRepository.findByLogin(userDb.getLogin()) != null ) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userRepository.save(userDb));

    }
}
