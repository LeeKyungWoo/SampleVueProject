package com.example.common.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5667847418528345845L;
	private T data;			// 결과 데이터
	private HttpStatus code;	// http 결과 코드
	private String msg;		// 결과 메시지
	
	public ResponseData(@Nullable T data, HttpStatus code) {
		this.data = data;
		this.code = code;
	}
	
	
	public ResponseData(@Nullable T data, HttpStatus code, String msg) {
		this.data = data;
		this.code = code;
		this.msg = msg;
	}
	
	
}
