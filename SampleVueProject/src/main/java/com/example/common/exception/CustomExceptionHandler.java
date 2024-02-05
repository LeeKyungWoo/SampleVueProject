package com.example.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
// 모든 컨트롤러단 exception 처리
public class CustomExceptionHandler {
	
	//모든 에러 -> 하위 에러에서 못받을 때
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseEntity> handleException(Exception e){
    	// NestedExceptionUtils.getMostSpecificCause() -> 가장 구체적인 원인, 즉 가장 근본 원인을 찾아서 반환
    	log.error("[Exception] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return ErrorResponseEntity.toResponseEntity(errorCode);
    }
    
    // @valid로 데이터 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e){
    	log.error("[MethodArgumentNotValidException] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
    	Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
    
    // 리소스를 찾을 수 없음
    // NoResourceFoundException, NoHandlerFoundException
    @ExceptionHandler({NoResourceFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponseEntity> handleNoResourceFoundException(Exception e){
    	// NestedExceptionUtils.getMostSpecificCause() -> 가장 구체적인 원인, 즉 가장 근본 원인을 찾아서 반환
    	log.error("[NoResourceFoundException] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = CommonErrorCode.NO_RESOURCE_FOUND_ERROR;
        return ErrorResponseEntity.toResponseEntity(errorCode);
    }

    // 사용자 정의 에러
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e) {
    	log.error("[CustomException] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }
    
    // 필수 파라미터가 없음.
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseEntity> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
    	log.error("[MissingServletRequestParameterException] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = CommonErrorCode.ILLEGAL_ARGUMENT_ERROR;
        return ErrorResponseEntity.toResponseEntity(errorCode);
    }
    
    // 요청 header가 없음
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseEntity> handleMissingRequestHeaderException(MissingRequestHeaderException e){
    	log.error("[MissingRequestHeaderException] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = CommonErrorCode.ILLEGAL_HEADER_ERROR;
        return ErrorResponseEntity.toResponseEntity(errorCode);
    }
    
}