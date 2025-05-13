package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoPackageBoxPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoPackageBoxReadManage {

	public SoPackageBoxPO findSoPackageBoxById(SoPackageBoxPO po);

	public PageResult<SoPackageBoxPO> findSoPackageBoxOfPage(SoPackageBoxPO po,Pagination page);

	public List<SoPackageBoxPO> findSoPackageBoxAll(SoPackageBoxPO po);

	/**
	 * 根据箱号查询箱子
	 * @param boxCode
	 * @param soChildId 
	 * @return
	 */
	public SoPackageBoxPO queryBoxByBoxCodeAndChildId(Long boxCode, Long soChildId);
}
	