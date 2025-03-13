package com.galleryProject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galleryProject.controller.IRestAddressController;
import com.galleryProject.controller.RestBaseController;
import com.galleryProject.controller.RootEntity;
import com.galleryProject.dto.DtoAddress;
import com.galleryProject.dto.DtoAdressIU;
import com.galleryProject.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {
   
    @Autowired
    private IAddressService addressService;
   
   
    @PostMapping("/save")
    @Override
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody  DtoAdressIU dtoAddressIU) {
        
        return ok(addressService.saveAddress(dtoAddressIU));
    }

}
