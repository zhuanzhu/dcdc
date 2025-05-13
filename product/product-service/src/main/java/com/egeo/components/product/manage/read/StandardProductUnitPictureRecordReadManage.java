package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitPictureRecordReadManage {

	public StandardProductUnitPictureRecordPO findStandardProductUnitPictureRecordById(StandardProductUnitPictureRecordPO po);

	public PageResult<StandardProductUnitPictureRecordPO> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordPO po,Pagination page);

	public List<StandardProductUnitPictureRecordPO> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordPO po);
}
	