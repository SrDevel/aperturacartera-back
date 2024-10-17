package com.registrocartera.service;

import com.registrocartera.model.AppUser;
import com.registrocartera.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        final var user =(AppUser) userRepository.findByUsername(username);
        if (user != null &&
                passwordEncoder.matches(oldPassword, user.getPassword()) &&
                !passwordEncoder.matches(newPassword, user.getPassword())
        ) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
