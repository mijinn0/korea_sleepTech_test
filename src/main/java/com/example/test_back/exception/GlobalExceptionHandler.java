package com.example.test_back.exception;

import com.example.test_back.dto.response.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 400 - 잘못된 요청 (Bad Request)
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDto<?>> handleBadRequest(Exception e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Bad Request: " + e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    // 403 - 권한 없음
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto<?>> handleAccessDenied(AccessDeniedException e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Access Denied: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // 404 - (요청)엔티티를 찾을 수 없음
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> handleNotFoundException(EntityNotFoundException e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Not Found: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 409 - 데이터 충돌 (무결성 위반): DB 제약 조건 위반
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Conflict: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // 500 - 서버 내부 오류
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleException(Exception e) {
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Internal Server Error : " + e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
