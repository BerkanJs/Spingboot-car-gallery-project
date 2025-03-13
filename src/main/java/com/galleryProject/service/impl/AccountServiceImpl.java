package com.galleryProject.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galleryProject.dto.DtoAccount;
import com.galleryProject.dto.DtoAccountIU;
import com.galleryProject.model.Account;
import com.galleryProject.repository.AccountRepository;
import com.galleryProject.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU, account);


        return account;
        
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
      DtoAccount dtoAccount = new DtoAccount();
      Account savedAccount=  accountRepository.save(createAccount(dtoAccountIU));
      BeanUtils.copyProperties(savedAccount, dtoAccount);
       return dtoAccount ;
    }


}
