package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitPictureReadManage {

	public StandardUnitPicturePO findStandardUnitPictureById(StandardUnitPicturePO po);

	public PageResult<StandardUnitPicturePO> findStandardUnitPictureOfPage(StandardUnitPicturePO po,Pagination page);

	public List<StandardUnitPicturePO> findStandardUnitPictureAll(StandardUnitPicturePO po);

    String findPictureUrlBySUId(Long id);
}
	