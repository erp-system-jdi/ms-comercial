package br.com.erpsystem.mssales.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private ExceptionResponse exceptionResponse;

    public CustomerNotFoundException(ExceptionResponse e){
        super();
        this.exceptionResponse = e;

    }
}
