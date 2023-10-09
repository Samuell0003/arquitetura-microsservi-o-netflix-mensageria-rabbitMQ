package com.iftm.newsletter.controllers;

import com.iftm.newsletter.models.user.AuthenticantionDTO;
import com.iftm.newsletter.models.user.LoginReponseDTO;
import com.iftm.newsletter.models.user.User;
import com.iftm.newsletter.models.user.UserDTO;
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
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginReponseDTO> login(@RequestBody @Validated AuthenticantionDTO data) throws Exception {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login().toUpperCase(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginReponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody @Validated UserDTO user) {
        return userService.save(user);
    }
}
