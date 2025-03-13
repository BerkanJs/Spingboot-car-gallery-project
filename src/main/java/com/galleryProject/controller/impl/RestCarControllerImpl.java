package com.galleryProject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galleryProject.controller.IRestCarController;
import com.galleryProject.controller.RestBaseController;
import com.galleryProject.controller.RootEntity;
import com.galleryProject.dto.DtoCar;
import com.galleryProject.dto.DtoCarIU;
import com.galleryProject.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl  extends RestBaseController implements IRestCarController{
    
    @Autowired
    private ICarService carService;
    
    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> savedCar(@Valid @RequestBody DtoCarIU dtoCarIU) {

        return ok(carService.saveCar(dtoCarIU));
   
        
    }

}
