package com.galleryProject.dto;

import com.galleryProject.model.Address;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoGallerist extends DtoBase {

    
    @NotNull
	private String firstName;
    
    @NotNull
	private String lastName;

    @NotNull
	private DtoAddress address;



}
