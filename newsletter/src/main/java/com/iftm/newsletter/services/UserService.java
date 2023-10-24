package com.iftm.newsletter.services;

import com.iftm.newsletter.models.user.User;
import com.iftm.newsletter.models.user.LoginUserDTO;
import com.iftm.newsletter.models.user.UserDTO;
import com.iftm.newsletter.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public ResponseEntity<UserDTO> save(LoginUserDTO user) {
        String passEncoder = new BCryptPasswordEncoder().encode(user.password());
        var userDb = new User(user.avatar(), user.email().toLowerCase(), user.firstName(), user.lastName(), passEncoder, user.role());
        if (userRepository.findByEmail(userDb.getEmail()) != null ) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new UserDTO(userRepository.save(userDb)));

    }

    public UserDTO findByEmail(String email) {
        return new UserDTO(userRepository.findByEmail(email.toLowerCase()));
    }

    public ResponseEntity<UserDTO> update(UserDTO user) {
        var userDb = userRepository.findById(new ObjectId(user.getId())).get();
//        userDb.setAvatar(user.getAvatar());
        userDb.setEmail(user.getEmail());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());

        return ResponseEntity.ok(new UserDTO(userRepository.save(userDb)));
    }
}
