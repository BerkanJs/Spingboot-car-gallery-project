package com.galleryProject.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galleryProject.dto.CurrencyRatesResponse;
import com.galleryProject.dto.DtoCar;
import com.galleryProject.dto.DtoCustomer;
import com.galleryProject.dto.DtoGallerist;
import com.galleryProject.dto.DtoSaledCar;
import com.galleryProject.dto.DtoSaledCarIU;
import com.galleryProject.enums.CarStatusType;
import com.galleryProject.exception.BaseException;
import com.galleryProject.exception.ErrorMessage;
import com.galleryProject.exception.MessageType;
import com.galleryProject.model.Car;
import com.galleryProject.model.Customer;
import com.galleryProject.model.SaledCar;
import com.galleryProject.repository.CarRepository;
import com.galleryProject.repository.CustomerRepository;
import com.galleryProject.repository.GalleristRepository;
import com.galleryProject.repository.SaledCarRepository;
import com.galleryProject.service.ICurrencyRatesService;
import com.galleryProject.service.ISaledCarService;
import com.galleryProject.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService
                .getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        
        BigDecimal usd=  new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());        
    
      
        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

        return customerUSDAmount;
    }

    public boolean checkCarStatus(Long carId){
      Optional<Car> optCar=  carRepository.findById(carId);
      if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())){
        return false;
      } return true;
    }

    public BigDecimal remaningCustomerAmount(Customer customer , Car car){
        BigDecimal customerUSDAmount =convertCustomerAmountToUSD(customer);
        BigDecimal remainingCustomerUSDAmount=customerUSDAmount.subtract(car.getPrice());
     
        CurrencyRatesResponse currencyRatesResponse=   currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd =  new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd()) ;

     
      return  remainingCustomerUSDAmount.multiply(usd);
    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());

        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));

        }

        
        BigDecimal customerUSDAmount=convertCustomerAmountToUSD(optCustomer.get());

        if(customerUSDAmount.compareTo(optCar.get().getPrice())==0 || customerUSDAmount.compareTo(optCar.get().getPrice())>0){
              return true;
        }return false;

    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU){
        SaledCar saledCar=new SaledCar();
        saledCar.setCreateTime(new Date());
        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }   

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if(!checkCarStatus(dtoSaledCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED,""));
        }
        if(!checkAmount(dtoSaledCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH,""));
        }



       SaledCar savedSaledCar= saledCarRepository.save(createSaledCar(dtoSaledCarIU));
       Car car = savedSaledCar.getCar();
       car.setCarStatusType(CarStatusType.SALED);

       carRepository.save(car);

       Customer customer=savedSaledCar.getCustomer();
       customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
       customerRepository.save(customer);

        return toDto(savedSaledCar);
    }

    public DtoSaledCar toDto(SaledCar saledCar){
        DtoSaledCar dtoSaledCar=new DtoSaledCar();
        DtoCustomer dtoCustomer =new DtoCustomer();
        DtoGallerist dtoGallerist=new DtoGallerist();
        DtoCar dtoCar=new DtoCar();

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
        
        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCar(dtoCar);
        return dtoSaledCar;


    }

}
