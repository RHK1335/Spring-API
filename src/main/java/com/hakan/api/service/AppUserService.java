package com.hakan.api.service;

import com.hakan.api.dto.request.CreateAppUserRequest;
import com.hakan.api.entity.AppUser;
import com.hakan.api.jwt.JwtUtils;
import com.hakan.api.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public AppUser createAppUser(CreateAppUserRequest createAppUserRequest) {
        // Kullanıcı adı ve e-posta var mı diye kontrol et
        if (appUserRepository.existsByUsername(createAppUserRequest.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (appUserRepository.existsByEmail(createAppUserRequest.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // Yeni kullanıcıyı oluştur
        AppUser appUser = new AppUser();
        appUser.setUsername(createAppUserRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(createAppUserRequest.getPassword())); // Şifreyi bcrypt ile şifrele
        appUser.setEmail(createAppUserRequest.getEmail());

        return appUserRepository.save(appUser);
    }

    public AppUser getCurrentUser() {
        return JwtUtils.getCurrentUser();
    }

    public AppUser getAppUserById(Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean deleteAppUser(Long id) {
        //add controls
        if (!appUserRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        appUserRepository.deleteById(id);
        return true;
    }

    /**
     * Updates an existing AppUser with the provided details.
     *
     * @param id the ID of the user to be updated
     * @param updateAppUserRequest the request object containing updated user details
     * @return the updated AppUser object
     * @throws RuntimeException if the user is not found, or if the new username or email is already in use
     */
    public AppUser updateAppUser(Long id, CreateAppUserRequest updateAppUserRequest) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if (!appUser.getUsername().equals(updateAppUserRequest.getUsername())) {
            if (appUserRepository.existsByUsername(updateAppUserRequest.getUsername())) {
                throw new RuntimeException("Username is already taken");
            }
            appUser.setUsername(updateAppUserRequest.getUsername());
        }

        if (!appUser.getEmail().equals(updateAppUserRequest.getEmail())) {
            if (appUserRepository.existsByEmail(updateAppUserRequest.getEmail())) {
                throw new RuntimeException("Email is already in use");
            }
            appUser.setEmail(updateAppUserRequest.getEmail());
        }

        if (!updateAppUserRequest.getPassword().isEmpty()) {
            appUser.setPassword(passwordEncoder.encode(updateAppUserRequest.getPassword()));
        }

        return appUserRepository.save(appUser);
    }
}
