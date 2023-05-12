package br.com.erpsystem.mssales.constants;

public enum ErrorCodes {
	INTERNAL_SERVER_ERROR("Internal server error", 500),
	INVALID_REQUEST("Invalid request", 400),
	VALIDATION_FAILED("Validation failed", 403);
    private final String message;
	private final Integer code;
    ErrorCodes(String message, Integer code) {
		this.message = message;
		this.code = code;
    }
	public String getMessage() {
    	return message;
	}
	public Integer getCode() {
		return code;
	}

}
