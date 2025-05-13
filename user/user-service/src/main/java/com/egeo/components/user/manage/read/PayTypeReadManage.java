package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.PayTypePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PayTypeReadManage {

	public PayTypePO findPayTypeById(PayTypePO po);

	public PageResult<PayTypePO> findPayTypeOfPage(PayTypePO po,Pagination page);

	public List<PayTypePO> findPayTypeAll(PayTypePO po);
	public PayTypePO findPayTypeByCode(Integer code);

    public List<PayTypePO> findPayTypeByCodes(List<Integer> codes);
}
	