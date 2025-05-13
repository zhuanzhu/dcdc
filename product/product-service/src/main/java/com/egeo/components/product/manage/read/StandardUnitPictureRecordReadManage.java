package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitPictureRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitPictureRecordReadManage {

	public StandardUnitPictureRecordPO findStandardUnitPictureRecordById(StandardUnitPictureRecordPO po);

	public PageResult<StandardUnitPictureRecordPO> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordPO po,Pagination page);

	public List<StandardUnitPictureRecordPO> findStandardUnitPictureRecordAll(StandardUnitPictureRecordPO po);
}
	