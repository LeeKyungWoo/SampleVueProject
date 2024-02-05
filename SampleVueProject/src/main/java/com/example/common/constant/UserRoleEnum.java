package com.example.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum UserRoleEnum {
    ADMIN("ROLE_ADMIN"),		// 관리자
    USER("ROLE_USER");			// 일반 사용자

    private String value;
    
    UserRoleEnum(String value) {
        this.value = value;
    }
}
