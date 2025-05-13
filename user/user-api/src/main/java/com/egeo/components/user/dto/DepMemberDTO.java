package com.egeo.components.user.dto;

import java.io.Serializable;

public class DepMemberDTO implements Serializable{

	private static final long serialVersionUID = 8024652341996074877L;
	private Long id;
	private String face;
	private String name;
	private String mail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
