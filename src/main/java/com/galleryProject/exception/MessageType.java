package com.galleryProject.exception;

import lombok.Getter;

@Getter



public enum MessageType {
	
	NO_RECORD_EXIST("1004","kayıt bulunamadı"),
	TOKEN_IS_EXPIRED("1005","tokenin süresi doldu"),
	USERNAME_NOT_FOUND("1006","username bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007","kullanıcı veya şifre hatalı"),
	REFRESH_TOKEN_NOT_FOUND("1009","refresh token bulunamadı"),
	REFRESH_TOKEN_IS_EXPIRED("1010","refresh token bitti"),
	CURRENCY_RATES_IS_OCCURED("1010","döviz kuru alınamadı"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","müşteri varlığı yetersiz"),
	CAR_STATUS_IS_ALREADY_SALED("1012","ilgili araba zaten satılmış"),
	GENERAL_EXCEPTION("9999","genel bir hata oluştu");
	
	private String code;
	
	private String message;
	
	MessageType(String code,String message) {
		this.code=code;
		this.message=message;
	}


	

}
