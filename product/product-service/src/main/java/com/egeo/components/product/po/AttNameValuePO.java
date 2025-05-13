package com.egeo.components.product.po;

import java.io.Serializable;

public class AttNameValuePO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 3797163040585135769L;

    private String key;
    
    private String name;
    
    private String value;
    
    private String mode;
    
    private String url;
    
    private int type;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
    
}
