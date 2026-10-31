package io.github.parthadevs.crudSpringBoot.exception;

import io.github.parthadevs.crudSpringBoot.dto.ExceptionResponceDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponceDto> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ExceptionResponceDto exceptionResponceDto = new ExceptionResponceDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errors.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(exceptionResponceDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponceDto> handleRuntimeException(RuntimeException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e.getMessage() != null && e.getMessage().contains("already exists")) {
            status = HttpStatus.CONFLICT;
        } else if (e.getMessage() != null && e.getMessage().contains("not found")) {
            status = HttpStatus.NOT_FOUND;
        }

        ExceptionResponceDto exceptionResponceDto = new ExceptionResponceDto(
                LocalDateTime.now(),
                status.value(),
                e.getMessage(),
                status.getReasonPhrase(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(exceptionResponceDto, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponceDto> handleGenericException(Exception e, HttpServletRequest request) {

        ExceptionResponceDto exceptionResponceDto = new ExceptionResponceDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong. Please try again later.",
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(exceptionResponceDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}