package com.galleryProject.service;

import com.galleryProject.dto.DtoAddress;
import com.galleryProject.dto.DtoAdressIU;

public interface IAddressService {

    public DtoAddress saveAddress (DtoAdressIU dtoAdressIU);

}
