package com.bmc.service;

import com.bmc.model.UserCredential;
import com.bmc.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
@Override
    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }
    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }
    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
