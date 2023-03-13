package br.com.erpsystem.mscomercial.client;

import br.com.erpsystem.mscomercial.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(url = "${services.ms-cliente.url}", name = "customer")
public interface ClienteFeign {

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClienteDTO buscarClientePorCpf(@PathVariable("cpf") String cpf);
}
