package com.galleryProject.service;

import com.galleryProject.dto.DtoSaledCar;
import com.galleryProject.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
    

}
