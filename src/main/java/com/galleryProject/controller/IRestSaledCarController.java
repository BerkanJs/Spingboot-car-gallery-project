package com.galleryProject.controller;

import com.galleryProject.dto.DtoSaledCar;
import com.galleryProject.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

    public RootEntity <DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);

}
