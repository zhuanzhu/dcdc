package com.egeo.components.product.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.ProductAttValuePO;
import com.egeo.orm.BaseWriteDAO;

import java.util.List;

public interface ProductAttValueWriteDAO extends BaseWriteDAO<ProductAttValuePO> {

    int deleteByProductAttNameId(ProductAttValuePO po);

    int deleteByMuchProductAttNameId(@Param("productAttNameIds")String productAttNameIds);
    /**
	 * 根据spu规格属性草稿id删除spu规格图片
	 * @param productAttValueId
	 * @param pictureUrl
	 * @return
	 */
	int delImgByProductAttNameId(@Param("productAttNameId")Long productAttNameId);

    void saveProductAttValue(@Param("poList")List<ProductAttValuePO> productAttValuePOList);
}
	