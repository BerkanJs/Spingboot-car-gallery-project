package com.galleryProject.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristIU  {


    @NotNull
	private String firstName;
    
    @NotNull
	private String lastName;

    @NotNull
	private Long addressId;
	
  

}
