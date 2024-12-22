package com.hakan.api.dto.request;


import lombok.Data;

@Data
public class UpdateAppUserRequest {
    private String username;
    private String password;
    private String email;
}
