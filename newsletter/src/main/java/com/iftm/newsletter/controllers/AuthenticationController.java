package com.iftm.newsletter.controllers;

import com.iftm.newsletter.models.user.*;
import com.iftm.newsletter.services.TokenService;
import com.iftm.newsletter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginReponseDTO> login(@RequestBody @Validated AuthenticantionDTO data) throws Exception {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email().toLowerCase(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginReponseDTO(token, userService.findByEmail(data.email())));
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> save(@RequestBody @Validated LoginUserDTO user) {
        return userService.save(user);
    }
    @PutMapping("update")
    public ResponseEntity<UserDTO> update(@RequestBody @Validated UserDTO user) {
        return userService.update(user);
    }
}
