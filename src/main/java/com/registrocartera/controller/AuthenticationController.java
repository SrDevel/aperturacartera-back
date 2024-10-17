package com.registrocartera.controller;

import com.registrocartera.dto.AuthenticationUserData;
import com.registrocartera.dto.JWTTokenData;
import com.registrocartera.dto.UpdatePasswordData;
import com.registrocartera.model.AppUser;
import com.registrocartera.security.TokenService;
import com.registrocartera.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public ResponseEntity<JWTTokenData> login(@RequestBody @Valid AuthenticationUserData userData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());
        Authentication authenticatedUser = authenticationManager.authenticate(authToken);
        String jwtToken = tokenService.generateToken((AppUser) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(jwtToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordData updatePasswordData) {
        boolean isUpdated = userService.updatePassword(updatePasswordData.username(), updatePasswordData.oldPassword(), updatePasswordData.newPassword());
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}