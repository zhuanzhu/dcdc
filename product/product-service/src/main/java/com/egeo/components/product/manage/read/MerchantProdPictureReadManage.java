package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.MerchantProdPictureCondition;
import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdPictureReadManage {

	public MerchantProdPicturePO findMerchantProdPictureById(MerchantProdPicturePO po);

	public PageResult<MerchantProdPicturePO> findMerchantProdPictureOfPage(MerchantProdPicturePO po,Pagination page);

	public List<MerchantProdPicturePO> findMerchantProdPictureAll(MerchantProdPicturePO po);
	/**
	 * 根据su草稿id查询su草稿图片信息
	 * @param merchantProductId
	 * @return
	 */
	public List<MerchantProdPictureCondition> findByMerchantProductId(Long merchantProductId);
}
	