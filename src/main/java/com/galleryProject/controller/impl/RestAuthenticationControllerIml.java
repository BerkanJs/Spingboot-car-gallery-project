package com.galleryProject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.galleryProject.controller.IRestAuthenticationController;
import com.galleryProject.controller.RestBaseController;
import com.galleryProject.controller.RootEntity;
import com.galleryProject.dto.AuthRequest;
import com.galleryProject.dto.AuthResponse;
import com.galleryProject.dto.DtoUser;
import com.galleryProject.dto.RefreshTokenRequest;
import com.galleryProject.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController

public class RestAuthenticationControllerIml extends RestBaseController implements IRestAuthenticationController  {
   
    @Autowired
    private IAuthenticationService authenticationService;
   
   
    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
       return ok(authenticationService.register(input));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {

        return ok(authenticationService.authenticate(input));

    }
    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
        return ok(authenticationService.refreshToken(input));
    }   

}
