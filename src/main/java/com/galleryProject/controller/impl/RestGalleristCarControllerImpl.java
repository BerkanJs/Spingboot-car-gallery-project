package com.galleryProject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galleryProject.controller.IRestGalleristCarController;
import com.galleryProject.controller.RestBaseController;
import com.galleryProject.controller.RootEntity;
import com.galleryProject.dto.DtoGalleristCar;
import com.galleryProject.dto.DtoGalleristCarIU;
import com.galleryProject.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {
     
    @Autowired
    private IGalleristCarService galleristCarService;



    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
       

        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }

}
