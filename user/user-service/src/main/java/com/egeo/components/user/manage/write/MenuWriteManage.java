package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.MenuPO;

public interface MenuWriteManage {

	Long save(MenuPO dto);
	
	String Update(MenuPO dto);

	void deleteWithTx(MenuPO po);
}
	