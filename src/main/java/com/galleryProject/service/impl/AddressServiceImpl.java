package com.galleryProject.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galleryProject.dto.DtoAddress;
import com.galleryProject.dto.DtoAdressIU;
import com.galleryProject.model.Address;
import com.galleryProject.repository.AddressRepository;
import com.galleryProject.service.IAddressService;




@Service
public class AddressServiceImpl implements IAddressService {


    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAdressIU dtoAdressIU){
        Address address = new Address();
        address.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAdressIU, address);
        return address;
    }



    

    @Override
    public DtoAddress saveAddress(DtoAdressIU dtoAdressIU) {
        DtoAddress dtoAddress =new DtoAddress();
        Address savedAddress=addressRepository.save(createAddress(dtoAdressIU));
        BeanUtils.copyProperties(savedAddress,dtoAddress );
        return dtoAddress;

       
     
    }

}
