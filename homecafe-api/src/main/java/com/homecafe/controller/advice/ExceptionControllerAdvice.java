package com.homecafe.controller.advice;

import com.homecafe.controller.ApiResponse;
import com.homecafe.exception.ConflictException;
import com.homecafe.exception.NotFoundException;
import com.homecafe.exception.UnauthorizedException;
import com.homecafe.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<Object> handleValidationException(ValidationException e) {
		log.error(e.getMessage(), e);
		return new ApiResponse<>("VALIDATION_EXCEPTION", e.getDescription(), null);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		String field = e.getBindingResult().getFieldError() == null ? "" : e.getBindingResult().getFieldError().getField();
		return new ApiResponse<>("VALIDATION_EXCEPTION", String.format("(%s) %s", field, e.getBindingResult().getFieldError().getDefaultMessage()), null);
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<Object> handleBindException(BindException e) {
		log.error(e.getMessage(), e);
		String field = e.getBindingResult().getFieldError() == null ? "" : e.getBindingResult().getFieldError().getField();
		return new ApiResponse<>("VALIDATION_EXCEPTION", String.format("(%s) %s", field, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()), null);
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiResponse<Object> handleUnauthorizedException(UnauthorizedException e) {
		log.error(e.getMessage(), e);
		return new ApiResponse<>("UNAUTHORIZED_EXCEPTION", "토큰이 만료되었습니다 다시 로그인 해주세요.", null);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiResponse<Object> handleNotFoundException(NotFoundException e) {
		log.error(e.getMessage(), e);
		return new ApiResponse<>("NOT_FOUND_EXCEPTION", e.getDescription(), null);
	}

	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ApiResponse<Object> handleConflictException(ConflictException e) {
		log.error(e.getMessage(), e);
		return new ApiResponse<>("CONFLICT_EXCEPTION", e.getDescription(), null);
	}

}