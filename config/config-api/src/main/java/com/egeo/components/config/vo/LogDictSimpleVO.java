package com.egeo.components.config.vo;

import java.io.Serializable;

/**
 * 
 * @author wyy
 * @date 2018-05-24 09:34:12
 */
public class LogDictSimpleVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
	