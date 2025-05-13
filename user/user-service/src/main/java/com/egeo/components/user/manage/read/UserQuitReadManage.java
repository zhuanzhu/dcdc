package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.UserQuitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserQuitReadManage {

	public UserQuitPO findUserQuitById(UserQuitPO po);

	public PageResult<UserQuitPO> findUserQuitOfPage(UserQuitPO po,Pagination page);

	public List<UserQuitPO> findUserQuitAll(UserQuitPO po);
}
	