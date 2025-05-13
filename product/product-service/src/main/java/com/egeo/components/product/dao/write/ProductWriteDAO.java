package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.ProductPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductWriteDAO extends BaseWriteDAO<ProductPO> {
	/**
	 * 删除所有点击添加规格属性没有点下一步所创建的空spu草稿信息
	 * @return
	 */
	int delByNullProduct();


    void saveProductList(@Param("poList") List<ProductPO> productPOList);

	void updateProductList(@Param("poList")List<ProductPO> productPOList);
	
	
	void cleanLink(@Param("id")Long id);
}
	