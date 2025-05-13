package com.egeo.components.order.vo;

import java.io.Serializable;

public class SoItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7247431191820893232L;

	private Long id;
	
	private boolean checked = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
