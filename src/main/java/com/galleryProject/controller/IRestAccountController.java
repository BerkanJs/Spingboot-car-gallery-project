package com.galleryProject.controller;

import com.galleryProject.dto.DtoAccount;
import com.galleryProject.dto.DtoAccountIU;

public interface IRestAccountController {
    public RootEntity <DtoAccount> saveAccount (DtoAccountIU dtoAccountIU);

}
