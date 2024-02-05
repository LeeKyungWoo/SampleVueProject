package com.example.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
	// 입맛에 맞게 에러코드 정의해서 사용
	
    INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 파라미터입니다."),
    INVALID_FORMAT_ERROR(HttpStatus.BAD_REQUEST,400, "올바르지 않은 포맷입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 타입입니다."),
    ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "필수 파라미터가 없습니다"),
    ILLEGAL_HEADER_ERROR(HttpStatus.BAD_REQUEST, 400, "요청 헤더가 없습니다"),
    
    INVALID_AUTH_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, 401, "권한 정보가 없는 토큰입니다."),
    
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, 403, "접근 권한이 없습니다."),
    
    USER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, 404, "요청 페이지를 찾을 수 없습니다."),
    NO_RESOURCE_FOUND_ERROR(HttpStatus.NOT_FOUND, 404, "리소스를 찾을 수 없습니다."),
    
    DUPLICATE_ERROR(HttpStatus.CONFLICT, 409, "데이터가 이미 존재합니다."),
    
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    
    
    ETC_ERROR(null, 9999, "오류가 발생했습니다. 관리자에게 문의하세요");
    
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

}
