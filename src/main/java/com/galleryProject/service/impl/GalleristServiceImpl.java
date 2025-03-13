package com.galleryProject.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galleryProject.dto.DtoAddress;
import com.galleryProject.dto.DtoGallerist;
import com.galleryProject.dto.DtoGalleristIU;
import com.galleryProject.exception.BaseException;
import com.galleryProject.exception.ErrorMessage;
import com.galleryProject.exception.MessageType;
import com.galleryProject.model.Address;
import com.galleryProject.model.Gallerist;
import com.galleryProject.repository.AddressRepository;
import com.galleryProject.repository.GalleristRepository;
import com.galleryProject.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {

        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist = new Gallerist();

        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optAddress.get());

        return gallerist;

    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist =new DtoGallerist();
        DtoAddress dtoAddress=new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(),dtoAddress);
        dtoGallerist.setAddress(dtoAddress);


        return dtoGallerist;

    }

}
