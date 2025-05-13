package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.MenuDTO;

public interface MenuWriteService {

	String saveOrUpdateWithTx(MenuDTO dto,String userName);

	public void deleteWithTx(MenuDTO dto);
}
	