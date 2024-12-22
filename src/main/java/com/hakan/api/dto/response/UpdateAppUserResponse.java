package com.hakan.api.dto.response;

import lombok.Data;

@Data
public class UpdateAppUserResponse  {
    private String username;
    private String password;
    private String email;
}
