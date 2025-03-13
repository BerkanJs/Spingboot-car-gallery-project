package com.galleryProject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galleryProject.controller.IRestCustomerController;
import com.galleryProject.controller.RestBaseController;
import com.galleryProject.controller.RootEntity;
import com.galleryProject.dto.DtoCustomer;
import com.galleryProject.dto.DtoCustomerIU;
import com.galleryProject.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {
    
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody  DtoCustomerIU dtoCustomerIU) {
       
        
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }



    

}
