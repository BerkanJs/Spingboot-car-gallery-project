package com.galleryProject.service;

import com.galleryProject.dto.DtoCustomer;
import com.galleryProject.dto.DtoCustomerIU;

public interface ICustomerService {
    public DtoCustomer saveCustomer (DtoCustomerIU dtoCustomerIU);

}
