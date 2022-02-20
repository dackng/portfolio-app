package com.backend.challenge.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ChallengeControllerAdvice {

	@ExceptionHandler({ MissingServletRequestParameterException.class, MethodArgumentNotValidException.class, HttpRequestMethodNotSupportedException.class
		, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ChallengeErrorResponse> handleBadRequestException(Exception ex) {
        log.error("BadRequestException: {}", ex.getMessage());
        String message;
        if(ex instanceof MethodArgumentNotValidException) {
        	List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult()
        			.getFieldErrors();
        	message = fieldErrors.stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(" | "));
        }else {
        	message = "A general error has ocurred";
        }
        
        return new ResponseEntity<>(new ChallengeErrorResponse(message)
        		, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
    @ExceptionHandler(value = {PortfolioNotAvailableException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ChallengeErrorResponse> handleNotFoundException(Exception ex) {
        log.error("UnprocessableEntityException: {}", ex.getMessage(), ex);
        
        return new ResponseEntity<>(new ChallengeErrorResponse(ex.getMessage())
        		, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ChallengeErrorResponse> handleException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(new ChallengeErrorResponse("A general error has ocurred")
        		, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
