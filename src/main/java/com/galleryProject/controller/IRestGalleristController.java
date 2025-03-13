package com.galleryProject.controller;

import com.galleryProject.dto.DtoGallerist;
import com.galleryProject.dto.DtoGalleristIU;

public interface IRestGalleristController {
    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
