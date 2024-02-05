package com.example.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6237465705813124256L;
	private final ErrorCode errorCode;
}
