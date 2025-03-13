package com.galleryProject.controller;

import com.galleryProject.dto.DtoCustomer;
import com.galleryProject.dto.DtoCustomerIU;

public interface IRestCustomerController {
    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

}
