package br.com.erpsystem.mssales.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EstimateNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private ExceptionResponse exceptionResponse;

    public EstimateNotFoundException(ExceptionResponse e){
        super();
        this.exceptionResponse = e;
    }
}
