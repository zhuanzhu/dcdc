package com.egeo.components.order.common;


import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Service
@Lazy
public abstract class IdGen {
	
	@SuppressWarnings("unused")
	private static SecureRandom random = new SecureRandom();
	
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
 
}
