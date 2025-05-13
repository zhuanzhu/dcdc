package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.orm.BaseWriteDAO;

public interface MerchantProdPictureWriteDAO extends BaseWriteDAO<MerchantProdPicturePO> {
	/**
	 * 根据su草稿图片关系id集合批量删除su草稿图片关系
	 * @param merchantProdPictureIds
	 * @return
	 */
	int deleteBymerchantProdPictureIds(@Param("ids")List<Long> merchantProdPictureIds);

    void saveMerchantProdPicture(@Param("poList")List<MerchantProdPicturePO> merchantProdPicturePOList);
}
	