package com.galleryProject.controller;

import com.galleryProject.dto.DtoGalleristCar;
import com.galleryProject.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
