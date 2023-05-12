package br.com.erpsystem.mssales.exceptions;

import br.com.erpsystem.mssales.constants.ErrorCodes;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 6437737997036907457L;

    private String code;
	private String message;
	private List<String> details;

    public ExceptionResponse(final ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = Collections.singletonList(details);
    }

    public ExceptionResponse(ErrorCodes errorCode, List<String> details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = details;
    }
}

