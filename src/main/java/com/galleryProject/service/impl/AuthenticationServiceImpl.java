package com.galleryProject.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.galleryProject.dto.AuthRequest;
import com.galleryProject.dto.AuthResponse;
import com.galleryProject.dto.DtoUser;
import com.galleryProject.dto.RefreshTokenRequest;
import com.galleryProject.exception.BaseException;
import com.galleryProject.exception.ErrorMessage;
import com.galleryProject.exception.MessageType;
import com.galleryProject.jwt.JWTservice;
import com.galleryProject.model.RefreshToken;
import com.galleryProject.model.User;
import com.galleryProject.repository.RefreshTokenRepository;
import com.galleryProject.repository.UserRepository;
import com.galleryProject.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTservice jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest input) {
        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(input));
        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {

        try {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    input.getUsername(), input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(input.getUsername());
            String accessToken = jwtService.generateToken(optUser.get());
            RefreshToken refreshToken = createRefreshToken(optUser.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        }

        catch (Exception e) {

            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));

        }

    }

    public boolean isValideRefreshToken(Date expiredDate){
        return new Date().before(expiredDate);
    }
 

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {

    Optional <RefreshToken> optRefreshToken=refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
    if(optRefreshToken.isEmpty()){
        throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND,input.getRefreshToken()));
    }

    if(!isValideRefreshToken(optRefreshToken.get().getExpiredDate())){
        throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED,input.getRefreshToken()));
    }
    User user =optRefreshToken.get().getUser();
    String accessToken = jwtService.generateToken(user);
    RefreshToken refreshToken = createRefreshToken(user);
    RefreshToken savedRefreshToken= refreshTokenRepository.save(refreshToken);





        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());

    }

}
