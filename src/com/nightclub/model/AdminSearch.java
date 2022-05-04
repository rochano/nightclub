package com.nightclub.model;

import java.io.Serializable;

public class AdminSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String japaneseName;
	private String englishName;
	private String agentName;
	private String nickName;
	private String email;

	public String getUserName() {
		return userName;
	}
	public String getJapaneseName() {
		return japaneseName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public String getAgentName() {
		return agentName;
	}
	public String getNickName() {
		return nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setJapaneseName(String japaneseName) {
		this.japaneseName = japaneseName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
