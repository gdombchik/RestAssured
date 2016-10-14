package com.test.rest.dto;

public class PostsDto {
	private String userID;
	private String id;
	private String title;
	private String body;
	
	public PostsDto(){};
	
	public PostsDto(String userID, String id, String title, String body) {
		super();
		this.userID = userID;
		this.id = id;
		this.title = title;
		this.body = body;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("userId: " + userID).
		append(" id: " + id).
		append(" title: " + title).
		append(" body: " + body);
	
		return value.toString();
	}
	
	
}
