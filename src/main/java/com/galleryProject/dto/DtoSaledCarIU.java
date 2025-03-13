package com.galleryProject.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoSaledCarIU {
    @NotNull
    private Long customerId;
    @NotNull
    private Long galleristId;
    @NotNull
    private Long carId;
    

}
