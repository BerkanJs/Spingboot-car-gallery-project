package com.galleryProject.service;

import com.galleryProject.dto.DtoCar;
import com.galleryProject.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

}
