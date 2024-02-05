package com.example.login.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.common.model.BaseModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor(force=true)
@EqualsAndHashCode(callSuper = true)
public class LoginUser extends BaseModel implements UserDetails{
	
	private static final long serialVersionUID = -937450571212628369L;
	private User user;
	private String role;
	@NotNull(message="아이디는 null이면 안됩니다")
	@Size(min=5, max=16, message="아이디는 5자 이상 16자 이하로 입력하세요")
	private String userId;
	 //'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용
	 //(특수문자는 정의된 특수문자만 사용 가능)
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message="길이는 최소 8자에서 최대 16자사이로 하고 숫자, 문자, 특수문자 최소 1자씩 있어야 합니다.")
	private String userPassword;
	@Pattern(regexp="^\\d{3}\\d{3,4}\\d{4}$", message="핸드폰 번호 형식이 아닙니다. xxxxxxxxxxx")
	private String userPhone;
	@Email
	private String userEmail;
	private String userAuth;

	
    @Builder
	public LoginUser(
			@NotNull(message = "아이디는 null이면 안됩니다") @Size(min = 5, max = 16, message = "아이디는 5자 이상 16자 이하로 입력하세요") String userId,
			@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "길이는 최소 8자에서 최대 16자사이로 하고 숫자, 문자, 특수문자 최소 1자씩 있어야 합니다.") String userPassword,
			@Pattern(regexp = "^\\d{3}\\d{3,4}\\d{4}$", message = "핸드폰 번호 형식이 아닙니다. xxxxxxxxxxx") String userPhone,
			@Email String userEmail, String userAuth,
			String useYn, String creId, String creDt, String modId, String modDt) {
		super(useYn, creId, creDt, modId, modDt);
		this.userId = userId;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAuth = userAuth;
	}

	
    //계정이 갖고있는 권한 목록을 리턴한다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(role != null) {
	        for(String role : role.split(",")){
	            authorities.add(new SimpleGrantedAuthority(role));
	        }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user != null ? user.getPassword() : "";
    }

    @Override
    public String getUsername() {
        return user != null ? user.getUsername() : "";
    }

	//계정이 만료되지 않았는지 리턴한다. ( true : 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
	//계정이 감져있지 않았는지 리턴한다. ( true : 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호가 만료되지 않았는지 리턴한다. ( true : 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정 활성화(사용가능)인지 리턴한다. ( true : 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

}
