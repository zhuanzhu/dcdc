package com.egeo.components.user.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.egeo.components.user.dto.UserExtendDTO;

/**
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class UserStoreAdminListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	List<UserExtendDTO> userList;
	
	List<Long> uids;

	public List<UserExtendDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserExtendDTO> userList) {
		this.userList = userList;
	}

	public List<Long> getUids() {
		return uids;
	}

	public void setUids(List<Long> uids) {
		this.uids = uids;
	}
	
	
	
	
	
}
	