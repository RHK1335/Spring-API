package com.hakan.api.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}