package com.bmc.service;

import com.bmc.model.UserCredential;

public interface AuthService {

    public String saveUser(UserCredential credential);

    public String generateToken(String username);

    public void validateToken(String token) ;
}
