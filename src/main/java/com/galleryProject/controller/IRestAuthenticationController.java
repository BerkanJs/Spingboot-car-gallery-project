package com.galleryProject.controller;

import com.galleryProject.dto.AuthRequest;
import com.galleryProject.dto.AuthResponse;
import com.galleryProject.dto.DtoUser;
import com.galleryProject.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register (AuthRequest input);

    public RootEntity <AuthResponse> authenticate (AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

}
