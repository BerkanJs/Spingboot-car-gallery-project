package com.galleryProject.exception;

public class BaseException extends RuntimeException {
	
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.prepareErrorMessage());
	}

}


//📦 Özet:



//MessageType → Hata türlerini ve mesajlarını tanımlıyor.

//ErrorMessage → Hata mesajlarını oluşturuyor.

//BaseException → Özelleştirilmiş bir exception, hata mesajını RuntimeException’a iletiyor.



//Şöyle adım adım toparlayalım:
//
//1️ Hata Türlerini ve Mesajlarını Tanımladık:
//MessageType enum’unda hata kodlarını ve mesajlarını belirttik. Örneğin:
//
//"1004" koduyla "kayıt bulunamadı"
//"9999" koduyla "genel bir hata oluştu"

//2️ ErrorMessage’a Bu Hata Türlerini İlettik:

//ErrorMessage sınıfında:

//messageType değişkeni, belirttiğimiz hata türünü taşıyor.
//ofStatic, bu hataya dinamik bir detay eklemek için var (örneğin: “Kullanıcı ID: 1234”).
//prepareErrorMessage() metodu, messageType'tan gelen mesajı alıyor ve varsa ofStatic'i ekleyerek tam hata mesajını oluşturuyor.

//3️ BaseException İle RuntimeException’a İlettik:

//BaseException, RuntimeException'dan türediği için unchecked exception oluyor.

//BaseException, ErrorMessage nesnesini alıyor.

//super(errorMessage.prepareErrorMessage()) satırıyla, oluşturduğumuz tam hata mesajını Java’nın exception sistemine iletiyor.

//4️ Hata Olduğunda BaseException’ı Fırlattık:

//Kodumuz bir hata durumuyla karşılaştığında, BaseException fırlatıyoruz: