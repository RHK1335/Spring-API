package com.hakan.api.controller;  // Yeni paket adı

import com.hakan.api.dto.request.AuthRequest;
import com.hakan.api.dto.response.AuthResponse;
import com.hakan.api.service.AuthService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        // Kullanıcı doğrulama işlemleri yapılır
        String token = authService.authenticateUser(authRequest);
        if (StringUtils.isNotBlank(token)) {
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

