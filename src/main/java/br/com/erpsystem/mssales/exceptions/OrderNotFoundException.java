package br.com.erpsystem.mssales.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ExceptionResponse exceptionResponse;

    public OrderNotFoundException(ExceptionResponse e){
        super();
        this.exceptionResponse = e;
    }

}
