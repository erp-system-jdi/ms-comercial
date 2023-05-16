package br.com.erpsystem.mssales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
public class MsSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSalesApplication.class, args);
	}

}
