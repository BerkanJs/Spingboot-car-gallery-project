package com.galleryProject.service;

import com.galleryProject.dto.AuthRequest;
import com.galleryProject.dto.AuthResponse;
import com.galleryProject.dto.DtoUser;
import com.galleryProject.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
    

}
