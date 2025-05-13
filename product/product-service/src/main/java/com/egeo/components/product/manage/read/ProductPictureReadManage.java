package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProductPictureReadManage {

	public ProductPicturePO findProductPictureById(ProductPicturePO po);

	public PageResult<ProductPicturePO> findProductPictureOfPage(ProductPicturePO po,Pagination page);

	public List<ProductPicturePO> findProductPictureAll(ProductPicturePO po);
}
	