package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardProductUnitPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitPictureReadManage {

	public StandardProductUnitPicturePO findStandardProductUnitPictureById(StandardProductUnitPicturePO po);

	public PageResult<StandardProductUnitPicturePO> findStandardProductUnitPictureOfPage(StandardProductUnitPicturePO po,Pagination page);

	public List<StandardProductUnitPicturePO> findStandardProductUnitPictureAll(StandardProductUnitPicturePO po);
}
	