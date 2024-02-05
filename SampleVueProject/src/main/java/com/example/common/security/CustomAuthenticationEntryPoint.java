package com.example.common.security;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.common.exception.CommonErrorCode;
import com.example.common.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 인증되지 않은 사용자가 필요한 요청 엔드포인트로 접근하려고 할 때 예외를 핸들링
@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private CustomException exceptionResponse = 
            new CustomException(CommonErrorCode.INVALID_AUTH_TOKEN_ERROR);
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    	log.error("Forbidden!!! message : " + authException.getMessage());
    	response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    	response.setStatus(HttpStatus.UNAUTHORIZED.value());
          try (OutputStream os = response.getOutputStream()) {
              ObjectMapper objectMapper = new ObjectMapper();
              objectMapper.writeValue(os, exceptionResponse);
              os.flush();
          }
    }
}