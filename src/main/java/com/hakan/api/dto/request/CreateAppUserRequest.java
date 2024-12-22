package com.hakan.api.dto.request;

import lombok.Data;

@Data
public class CreateAppUserRequest {
    private String username;
    private String password;
    private String email;
}
