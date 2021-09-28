package com.contaduria.movimientofinanciero.error;

import com.contaduria.movimientofinanciero.exceptions.DataIntegrityViolationException;
import com.contaduria.movimientofinanciero.exceptions.PermissionException;
import com.contaduria.movimientofinanciero.exceptions.ResourceNotFoundException;
import com.contaduria.movimientofinanciero.exceptions.ValidationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		this.logger.error("ERROR 500 " + " - " + "message: "+ ex.getMessage()+ " - " + "cause: " + ex.getCause() + " - " +" specific:"+  ex.getLocalizedMessage());
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"¡Se ha producido un error, comuniquese con el administrador!. " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		StringBuilder builder = new StringBuilder();
		builder.append("Request JSON mal formado: ");
		if (mostSpecificCause != null) {
			String[] message = mostSpecificCause.getMessage().split(";");
			builder.append(message[0]);
		} else {
			builder.append(ex.getMessage());
		}
		this.logger.debug("JSON mal formado:" + ex.getMessage());
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, builder.toString()));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "El parametro <" + ex.getParameterName() + "> no se encuentra.";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "No handler encontrado para <" + ex.getHttpMethod() + "> <" + ex.getRequestURL() + ">.";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append("<" + ex.getMethod() + ">");
		builder.append(" metodo no es soportado para esta solicitud. Los metodos soportados son ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(" <" + t + ">,"));
		return buildResponseEntity(new ApiError(HttpStatus.METHOD_NOT_ALLOWED, builder.toString()));
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "" + errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleValidationException(Exception ex) {
		this.logger.debug("BAD REQUEST" + ex.getLocalizedMessage());
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleResourceNotFoundException(Exception ex) {
		this.logger.debug("NOT FOUND" + ex.getLocalizedMessage());
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
	}
// se fue con la clase authorization
//	@ResponseStatus(HttpStatus.FORBIDDEN)
//	@ExceptionHandler(PermissionException.class)
//	public ResponseEntity<Object> handlePermissionException(Exception ex, WebRequest request) {
//		this.logger.debug("FORBIDDEN: " + ex.getLocalizedMessage());
//		return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ex));
//	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};
		HashMap<String, Object> body = mapper.readValue(ex.getResponseBodyAsString(), typeRef);
		return buildResponseEntity(new ApiError(ex.getStatusCode(), body.get("message").toString()));
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex, WebRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};
		HashMap<String, Object> body = mapper.readValue(ex.getResponseBodyAsString(), typeRef);
		return buildResponseEntity(new ApiError(ex.getStatusCode(), body.get("message").toString()));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	protected ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex) {
		String message = ex.getLocalizedMessage() ;
		this.logger.debug("ERROR- CONFLICT: Message:"+ ex.getMessage() + " - " );
		return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, message));
	}
}