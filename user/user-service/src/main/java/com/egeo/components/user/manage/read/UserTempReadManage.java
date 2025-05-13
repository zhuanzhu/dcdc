package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.UserTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserTempReadManage {

	public UserTempPO findUserTempById(UserTempPO po);

	public PageResult<UserTempPO> findUserTempOfPage(UserTempPO po,Pagination page);

	public List<UserTempPO> findUserTempAll(UserTempPO po);
	/**
	 * 根据用户id查询预导入数据id
	 * @param createUserid 用户id
	 * @param platformId 平台id
	 * @return
	 */
	public List<Long> findIdsByCreateUserid(Long createUserid, Long platformId);
}
	