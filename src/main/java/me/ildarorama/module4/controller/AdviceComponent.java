package me.ildarorama.module4.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.ildarorama.module4.service.dto.ErrorResponse;
import me.ildarorama.module4.service.exception.AlreadyExistException;
import me.ildarorama.module4.service.exception.ApiException;
import me.ildarorama.module4.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceComponent {

    @ExceptionHandler({ApiException.class})
    public final ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        HttpStatus status;
        if (ex instanceof ObjectNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof AlreadyExistException) {
            status = HttpStatus.CONFLICT;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(new ErrorResponse(ex.getClass().getCanonicalName()));
    }
}