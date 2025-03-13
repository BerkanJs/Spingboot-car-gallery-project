package com.galleryProject.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;

}
