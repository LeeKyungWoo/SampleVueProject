package com.example.test.model;

import com.example.common.model.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor(force=true)
public class TestModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8360010744025683387L;
	private String sessionId;
	private long creationTime;
	private long lastAccessedTime; 
	private int maxInactiveInterval;
	private Object attribute;
	private boolean isNew;
	
	@Builder
	public TestModel(String sessionId, long creationTime, long lastAccessedTime, int maxInactiveInterval, Object attribute, boolean isNew,
			String useYn, String creId, String creDt, String modId, String modDt) {
		super(useYn, creId, creDt, modId, modDt);
		this.sessionId = sessionId;
		this.creationTime = creationTime;
		this.lastAccessedTime = lastAccessedTime;
		this.maxInactiveInterval = maxInactiveInterval;
		this.attribute = attribute;
		this.isNew = isNew;
	}
	
	
}
