package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantPictureReadManage {

	public MerchantPicturePO findMerchantPictureById(MerchantPicturePO po);

	public PageResult<MerchantPicturePO> findMerchantPictureOfPage(MerchantPicturePO po,Pagination page);

	public List<MerchantPicturePO> findMerchantPictureAll(MerchantPicturePO po);

    Long findLastId();
}
	