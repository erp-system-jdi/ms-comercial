package br.com.erpsystem.mssales.exceptions.handler;


import br.com.erpsystem.mssales.exceptions.ProductOutOfStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ProductOutOfStockException.class)
    public final ResponseEntity<Object> handleCustomerFeignException(ProductOutOfStockException ex){
        log.error("Product Out of Stock Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getException());
    }
}
