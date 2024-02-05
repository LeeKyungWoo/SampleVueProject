package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.response.ResponseData;
import com.example.login.model.LoginUser;
import com.example.login.service.LoginService;
import com.example.test.model.TestModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping(value = "/session/create")
    public ResponseEntity<ResponseData<TestModel>> createTestSession(HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession(true);
        session.setAttribute("USER_SESSION", System.currentTimeMillis());
        //session.setMaxInactiveInterval(60);
        //return new ResponseEntity<>(new ResponseData(), HttpStatus.OK);
        //return ResponseEntity.ok().body(new ResponseResult<>(new ResponseData(), HttpStatus.OK));
        // builder를 사용하자!
        return ResponseEntity.ok().body(
        		new ResponseData<>(TestModel.builder()
									.sessionId(session.getId())
									.creationTime(session.getCreationTime())
									.lastAccessedTime(session.getLastAccessedTime())
									.maxInactiveInterval(session.getMaxInactiveInterval())
									.attribute(session.getAttribute("USER_SESSION"))
									.isNew(session.isNew())
									.build()
						, HttpStatus.OK, "세션 생성 완료")
        		);
        		
    }
    
    @PostMapping(value = "/session/destroy")
    public ResponseEntity<ResponseData<TestModel>> destroyTestSession(HttpServletRequest request) throws Exception{
    	HttpSession session = request.getSession(false);
    	if(session == null) {
    		 return ResponseEntity.ok().body(
    	        		new ResponseData<>(TestModel.builder().build()
    							, HttpStatus.OK, "이미 세션이 존재하지 않음")
    	        		);
    	}
    	session.invalidate();
    	return ResponseEntity.ok().body(
					new ResponseData<>(TestModel.builder().build()
					, HttpStatus.OK, "세션 제거 완료")
       		);
    }
    
	@GetMapping(value = "/seletUserTest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")	// admin 권한을 요구하는 컨트롤러인경우 이처럼 하면됨
    public ResponseEntity<ResponseData<LoginUser>> seletUserTest(@Valid @RequestParam(name = "userId") String userId){
		LoginUser result = loginService.getUserById(LoginUser.builder().userId(userId).build());
		String resultMsg = result == null ? "데이터가 없습니다." : "성공";
		return ResponseEntity.ok().body(
				new ResponseData<>(result, HttpStatus.OK, resultMsg)
   		);
    }
    


}
