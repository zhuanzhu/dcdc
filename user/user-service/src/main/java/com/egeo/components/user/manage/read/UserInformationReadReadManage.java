package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.UserInformationReadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UserInformationReadReadManage {

	public UserInformationReadPO findUserInformationReadById(UserInformationReadPO po);

	public PageResult<UserInformationReadPO> findUserInformationReadOfPage(UserInformationReadPO po,Pagination page);

	public List<UserInformationReadPO> findUserInformationReadAll(UserInformationReadPO po);
}
	