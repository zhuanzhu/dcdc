package com.egeo.components.stock.service;

import java.io.Serializable;

/**
 * 请求接口
 * @author Administrator
 *
 */
public interface Request extends Serializable{
	
	void process();
	Long getId();
	
}
