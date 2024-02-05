package com.example.common.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(force=true)
public class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5249043279296940319L;
	private String useYn;
	private String creId;
	private String creDt;
	private String modId;
	private String modDt;
	
	public BaseModel(String useYn, String creId, String creDt, String modId, String modDt) {
		this.useYn = useYn;
		this.creId = creId;
		this.creDt = creDt;
		this.modId = modId;
		this.modDt = modDt;
	}
	
	
	
	
	// valid 
/*	
@NotBlank 
	null 이 아닌 값이다.
	공백이 아닌 문자를 하나 이상 포함한다
@NotEmpty
	Type : CharSequence (length of character) Collection (collection size) Map (map size Array (array length)
	null 이거나 empty(빈 문자열)가 아니어야 한다.
@NotNull
	Type : 어떤 타입이든 수용한다.
	null 이 아닌 값이다.
@Null
	Type :어떤 타입이든 수용한다.
	null 값이다.
@DecimalMax : 지정된 최대 값보다 작거나 같아야 한다.
	Require : String value  => max 값을 지정한다.
@DecimalMin : 지정된 최소 값보다 크거나 같아야 한다.
    Require : String value  => min 값을 지정한다.
@Max : 지정된 최대 값보다 작거나 같아야 한다.
    Require : int value  => max 값을 지정한다.
@Min : 지정된 최소 값보다 크거나 같아야 한다.
    Require : int value  => min 값을 지정한다.
      
@Positive : 양수인 값이다.
@PositiveOrZero : 0이거나 양수인 값이다.
@Negative : 음수인 값이다.
@NegativeOrZero : 0이거나 음수인 값이다.
@Future : Now 보다 미래의 날짜, 시간이어야 한다.
@FutureOrPresent : Now 거나 미래의 날짜, 시간이어야 한다.
@Past : Now 보다 과거 의의 날짜, 시간이어야 한다.
@PastOrPresent : Now 거나 과거의 날짜, 시간이어야 한다.
@Email : 올바른 형식의 이메일 주소여야 한다. (@가 들어가야한다.)
@Digits : 허용된 범위 내의 숫자이다.
	Require : int integer  => 이 숫자에 허용되는 최대 정수 자릿수
  	Require : int fraction  => 이 숫자에 허용되는 최대 소수 자릿수
@AssertTrue : 값이 항상 True 여야 한다.
@AssertFalse : 값이 항상 False 여야 한다.
@Size : 이 크기가 지정된 경계(포함) 사이에 있어야 한다.
	Require : int max  => element의 크기가 작거나 같다.
  	Require : int min  => element의 크기가 크거나 같다.
@Pattern : 지정한 정규식과 대응되는 문자열 이어야 한다. Java의 Pattern 패키지의 컨벤션을 따른다
  	Require : String regexp  => 정규식 문자열을 지정한다
*/      
}
