package br.com.erpsystem.mssales.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private ExceptionResponse exceptionResponse;

    public BusinessException(ExceptionResponse e){
        super();
        this.exceptionResponse = e;

    }


}
