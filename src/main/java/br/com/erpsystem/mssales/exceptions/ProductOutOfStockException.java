package br.com.erpsystem.mssales.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductOutOfStockException extends RuntimeException{

    private static final long serialVersionUID = -6609003254375075416L;
    private ExceptionResponse exception;

    public ProductOutOfStockException(ExceptionResponse exception){
        super();
        this.exception = exception;
    }
}
