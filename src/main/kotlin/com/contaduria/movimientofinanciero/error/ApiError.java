package com.contaduria.movimientofinanciero.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
	
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private boolean success;
	private boolean hasError;

	private ApiError() {
		setTimestamp(LocalDateTime.now());
		setSuccess(false);
		setHasError(true);
	}

	public ApiError(HttpStatus status) {
		this();
		this.setStatus( status );
	}

	public ApiError(HttpStatus status, String message) {
		this( status );
		this.setMessage( message );
	}
	
	public ApiError(HttpStatus status, Throwable ex) {
		this( status, ex.getMessage() );
	}

}