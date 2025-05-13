package com.egeo.components.product.vo;

import java.io.Serializable;

public class AttNameValueVO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String key;
    
    private String name;
    
    private String value;
    
    private String mode;
    
    private String url;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}
