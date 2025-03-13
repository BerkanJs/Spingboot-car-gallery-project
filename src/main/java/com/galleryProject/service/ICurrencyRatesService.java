package com.galleryProject.service;

import com.galleryProject.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRates(String startDate,String endDate);


}
