package com.galleryProject.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAdressIU {


    @NotEmpty
	private String city;
	
    @NotEmpty
	private String district;
	
    @NotEmpty
	private String neighborhood;
	
    @NotEmpty
	private String street;

}
