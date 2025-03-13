package com.galleryProject.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galleryProject.dto.DtoAccount;
import com.galleryProject.dto.DtoAddress;
import com.galleryProject.dto.DtoCustomer;
import com.galleryProject.dto.DtoCustomerIU;
import com.galleryProject.exception.BaseException;
import com.galleryProject.exception.ErrorMessage;
import com.galleryProject.exception.MessageType;
import com.galleryProject.model.Account;
import com.galleryProject.model.Address;
import com.galleryProject.model.Customer;
import com.galleryProject.repository.AccountRepository;
import com.galleryProject.repository.AddressRepository;
import com.galleryProject.repository.CustomerRepository;
import com.galleryProject.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional <Address> optAddress= addressRepository.findById(dtoCustomerIU.getAddressId());
        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()));
        }

        Optional <Account> optAccount=accountRepository.findById(dtoCustomerIU.getAccountId());

        if(optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()));
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU, customer);
        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());
        return customer;




    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer=new DtoCustomer();
        DtoAddress dtoAddress=new DtoAddress();
        DtoAccount dtoAccount=new DtoAccount();

        Customer savedCustomer=customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);



        return dtoCustomer;
    }

}
