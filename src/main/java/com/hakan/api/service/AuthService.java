package com.hakan.api.service;  // Yeni paket adı

import com.hakan.api.dto.request.AuthRequest;
import com.hakan.api.entity.AppUser;
import com.hakan.api.jwt.JwtUtils;
import com.hakan.api.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public String authenticateUser(AuthRequest authRequest) {
        AppUser foundAppUser = appUserRepository.findByUsername(authRequest.getUsername());
        if (foundAppUser != null && passwordEncoder.matches(authRequest.getPassword(), foundAppUser.getPassword())) {
            return JwtUtils.generateToken(foundAppUser.getId(), foundAppUser.getUsername());
        }
        return null;  // Kullanıcı adı veya şifre yanlışsa
    }
}
