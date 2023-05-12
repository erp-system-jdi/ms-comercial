package br.com.erpsystem.mssales.client;

import br.com.erpsystem.mssales.dto.ProductDTO;
import br.com.erpsystem.mssales.dto.http.response.SearchProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(url = "${services.ms-products.url}", name = "products")
public interface ProductClient {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    SearchProductResponseDTO findProductById(@PathVariable("id") String id);
}
