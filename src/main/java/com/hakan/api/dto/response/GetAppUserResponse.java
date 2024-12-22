package com.hakan.api.dto.response;

import lombok.Data;

@Data
public class GetAppUserResponse {
    private String username;
    private String email;

    public GetAppUserResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }


}
