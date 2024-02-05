package com.example.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.common.constant.SqlMapEnum;
import com.example.common.security.CustomAuthenticationEntryPoint;
import com.example.login.model.LoginUser;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository	
public class LoginDao {
	private final SqlSessionTemplate sqlSession;

    public LoginDao(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

	public LoginUser getUserById(@Valid @RequestBody LoginUser vo) {
		log.debug("####getUserById param : {}", vo.toString());
		return sqlSession.selectOne(SqlMapEnum.BASE_SQL_MAP_ID.getValue() + "." + SqlMapEnum.LOGIN_SQL_MAP_ID.getValue() + "." +  "getUserById", vo);
	}
}
