package com.cognitive.zadatak;

import com.cognitive.zadatak.services.Api;
import com.cognitive.zadatak.services.ReadXml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZadatakApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZadatakApplication.class, args);
		Api api = new Api();
		api.getMethod();
		ReadXml xml = new ReadXml();
		xml.ImportXml();
	}

}
