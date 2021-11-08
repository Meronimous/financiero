package com.contaduria.movimientofinanciero.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class NotEnoughFundsException extends RuntimeException {

	private int code;
    private int id;

    public NotEnoughFundsException(String message) {
        super(message);
    }

    public NotEnoughFundsException(String message, int code) {
        super(message);
        setCode(code);
    }

    public NotEnoughFundsException(String message, int code, int id) {
        super(message);
        setCode(code);
        setId(id);
    }

    private static final long serialVersionUID = -1568466835743411225L;

}