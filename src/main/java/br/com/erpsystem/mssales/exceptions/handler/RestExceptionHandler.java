package br.com.erpsystem.mssales.exceptions.handler;


import br.com.erpsystem.mssales.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ProductOutOfStockException.class)
    public final ResponseEntity<Object> handleProductOutOfStockException(ProductOutOfStockException ex){
        log.error("Product Out of Stock Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getException());
    }

    @ExceptionHandler(ExpiredEstimateException.class)
    public final ResponseEntity<Object> handleExpiredEstimateException(ExpiredEstimateException ex){
        log.error("Expired Estimate Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getExceptionResponse());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex){
        log.error("Customer not registered Error : {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getExceptionResponse());
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException ex){
        log.error("Business Exception Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getExceptionResponse());
    }

    @ExceptionHandler(EstimateNotFoundException.class)
    public final ResponseEntity<Object> handleEstimateNotFoundException(EstimateNotFoundException ex){
        log.error("Estimate Not Found Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getExceptionResponse());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex){
        log.error("Order Not Found Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getExceptionResponse());
    }






}
