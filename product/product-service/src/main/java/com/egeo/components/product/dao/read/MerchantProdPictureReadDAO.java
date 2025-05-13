package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.MerchantProdPictureCondition;
import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.orm.BaseReadDAO;

public interface MerchantProdPictureReadDAO extends BaseReadDAO<MerchantProdPicturePO>{
	/**
	 * 根据su草稿id查询su草稿图片信息
	 * @param merchantProductId
	 * @return
	 */
	List<MerchantProdPictureCondition> findByMerchantProductId(@Param("merchantProductId")Long merchantProductId);
}
	