package com.galleryProject.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galleryProject.controller.IRestGalleristController;
import com.galleryProject.controller.RestBaseController;
import com.galleryProject.controller.RootEntity;
import com.galleryProject.dto.DtoGallerist;
import com.galleryProject.dto.DtoGalleristIU;
import com.galleryProject.service.IGalleristService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {
   
    @Autowired
    private IGalleristService galleristService;



    @PostMapping("/save")
    @Override
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
       
    }

}
