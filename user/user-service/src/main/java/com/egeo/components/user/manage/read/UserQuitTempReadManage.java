package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.UserQuitTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserQuitTempReadManage {

	public UserQuitTempPO findUserQuitTempById(UserQuitTempPO po);

	public PageResult<UserQuitTempPO> findUserQuitTempOfPage(UserQuitTempPO po,Pagination page);

	public List<UserQuitTempPO> findUserQuitTempAll(UserQuitTempPO po);
}
	