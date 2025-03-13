package com.galleryProject.service;

import com.galleryProject.dto.DtoAccount;
import com.galleryProject.dto.DtoAccountIU;

public interface IAccountService {
        public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

}
