package com.hakan.api.controller;

import com.hakan.api.dto.request.CreateAppUserRequest;
import com.hakan.api.dto.response.BaseResponse;
import com.hakan.api.dto.response.GetAppUserResponse;
import com.hakan.api.entity.AppUser;
import com.hakan.api.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    // Yeni AppUser oluştur
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<GetAppUserResponse>> createAppUser(@RequestBody CreateAppUserRequest createAppUserRequest) {
        try {
            AppUser appUser = appUserService.createAppUser(createAppUserRequest);
            GetAppUserResponse appUserResponse = new GetAppUserResponse(appUser.getUsername(), appUser.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(true, "", appUserResponse), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new BaseResponse<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    // Yeni AppUser oluştur
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<GetAppUserResponse>> getAppUser() {
        try {
            AppUser appUser = appUserService.getCurrentUser();
            GetAppUserResponse appUserResponse = new GetAppUserResponse(appUser.getUsername(), appUser.getEmail());
            return new ResponseEntity<>(new BaseResponse<>(true, "", appUserResponse), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new BaseResponse<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
