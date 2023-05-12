package br.com.erpsystem.mssales.client;

import br.com.erpsystem.mssales.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(url = "${services.ms-customer.url}", name = "customer")
public interface CustomerClient {

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDTO findCustomerByCpf(@PathVariable("cpf") String cpf);
}
