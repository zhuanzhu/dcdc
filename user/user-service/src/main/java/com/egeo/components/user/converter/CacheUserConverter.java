package com.egeo.components.user.converter;

import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.entity.CacheUser;

/**
 * DTO和VO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-05-08 17:38:48
 */
public class CacheUserConverter {
	



	public static void setCacheUserbyUser(CacheUser tar, UserVO src) {
		
		tar.setId(src.getId());
		tar.setLoginName(src.getLoginName());
		tar.setName(src.getName());
		tar.setMobile(src.getMobile());
		tar.setMail(src.getMail());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyType(src.getCompanyType());
		tar.setType(src.getType());
	
		
	}
	
	
	public static void setCacheUserbyUser(CacheUser tar, UserDTO src) {
		
		tar.setId(src.getId());
		tar.setLoginName(src.getLoginName());
		tar.setMobile(src.getMobile());
		tar.setMail(src.getMail());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setCompanyId(src.getCompanyId());
		tar.setType(src.getType());
	
		
	}





}
	