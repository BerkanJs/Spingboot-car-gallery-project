package com.galleryProject.controller;

import com.galleryProject.dto.DtoAddress;
import com.galleryProject.dto.DtoAdressIU;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress (DtoAdressIU dtoAddressIU);

}
