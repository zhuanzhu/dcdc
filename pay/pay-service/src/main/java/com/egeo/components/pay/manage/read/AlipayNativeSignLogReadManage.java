package com.egeo.components.pay.manage.read;

import java.util.List;

import com.egeo.components.pay.po.AlipayNativeSignLogPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AlipayNativeSignLogReadManage {

	public AlipayNativeSignLogPO findAlipayNativeSignLogById(AlipayNativeSignLogPO po);

	public PageResult<AlipayNativeSignLogPO> findAlipayNativeSignLogOfPage(AlipayNativeSignLogPO po,Pagination page);

	public List<AlipayNativeSignLogPO> findAlipayNativeSignLogAll(AlipayNativeSignLogPO po);
}
	