package com.galleryProject.controller;

import com.galleryProject.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate,String endString);

}
