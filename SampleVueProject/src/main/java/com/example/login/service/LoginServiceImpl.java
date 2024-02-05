package com.example.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.example.login.dao.LoginDao;
import com.example.login.model.LoginUser;
import com.example.login.model.LoginUser.LoginUserBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements UserDetailsService, LoginService {

	@Autowired
    private LoginDao loginDao;

    //-- Param 값으로 Password 를 조회하여 반환하는 method --//
	// 반환값을 이용해 Spring Security 가 자동으로 일치여부를 검사해준다.
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        //adminUser 정보 조회
    	LoginUser paramVo = LoginUser.builder().userId(userId).build();
        LoginUser loginUser = loginDao.getUserById(paramVo);
        if(loginUser == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
    	return loginUser;
      
    }

	@Override
	public LoginUser getUserById(LoginUser loginUser) {
		return loginDao.getUserById(loginUser);
	}
}