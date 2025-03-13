package com.galleryProject.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.galleryProject.dto.CurrencyRatesResponse;
import com.galleryProject.exception.BaseException;
import com.galleryProject.exception.ErrorMessage;
import com.galleryProject.exception.MessageType;
import com.galleryProject.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/"; //https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A.YTL&startDate=13-03-2025&endDate=13-03-2025&type=json?key=%26key=LpagLowHzQ
        String series = "TP.DK.USD.A.YTL";
        String type = "json";

        String endpoint = rootUrl + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "LpagLowHzQ");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }

            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, "Failed to fetch currency rates"));

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
        }
    }
}
