package com.galleryProject.controller;

import com.galleryProject.dto.DtoCar;
import com.galleryProject.dto.DtoCarIU;

public interface IRestCarController {
    public RootEntity<DtoCar> savedCar(DtoCarIU dtoCarIU);

}
