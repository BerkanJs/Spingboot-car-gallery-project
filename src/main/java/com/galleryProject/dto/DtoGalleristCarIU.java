package com.galleryProject.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCarIU {

    	
	@NotNull
	private Long galleristId;
	
    @NotNull
	private Long carId ;



}
