package br.com.erpsystem.mscomercial.exceptions;

import lombok.Getter;

@Getter
public class CustomerNotFoundException extends RuntimeException{

    static final long serialVersionUID = -2628030626287312237L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
