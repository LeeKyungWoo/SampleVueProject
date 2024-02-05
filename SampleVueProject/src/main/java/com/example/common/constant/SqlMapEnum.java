package com.example.common.constant;

import lombok.Getter;

@Getter
public enum SqlMapEnum {
	BASE_SQL_MAP_ID("com.example"),		// 기본경로
    LOGIN_SQL_MAP_ID("login.mapper.loginMapper");		// 로그인		

    private String value;
    
    SqlMapEnum(String value) {
        this.value = value;
    }
}
