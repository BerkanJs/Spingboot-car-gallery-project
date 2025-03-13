package com.galleryProject.dto;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase {

   
	private String firstName;

	private String lastName;

	private String tckn;

	private Date birthOfDate;

	private DtoAddress address;

	private DtoAccount account;

}
