package br.com.erpsystem.mscomercial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsComercialApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsComercialApplication.class, args);
	}

}
