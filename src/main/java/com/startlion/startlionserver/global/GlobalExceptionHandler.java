package com.startlion.startlionserver.global;

import com.startlion.startlionserver.dto.response.ErrorResponse;
import com.startlion.startlionserver.global.enums.CustomCode;
import com.startlion.startlionserver.global.exception.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponse.of(CustomCode.SL_40000.toString(), e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorResponse(CustomCode.SL_40000.toString(), e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(ErrorResponse.of(CustomCode.SL_43000.toString(), e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ErrorResponse.of(CustomCode.SL_41000.toString(), e.getMessage()));
    }

    @ExceptionHandler(PersonalInfoApproveException.class)
    public ResponseEntity<ErrorResponse> handlePersonalInfoApproveException(PersonalInfoApproveException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.of(CustomCode.SL_40002.toString(), e.getMessage()));
    }

    @ExceptionHandler({InvalidApplyException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleInvalidApplyException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponse.of(CustomCode.SL_40001.toString(), e.getMessage()));
    }

    // application email 중복 검사 예외 처리
    @ExceptionHandler( EmailAlreadyInUseException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyInUseException(EmailAlreadyInUseException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
