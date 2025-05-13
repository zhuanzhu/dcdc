package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.vo.AttName;
import com.egeo.components.product.vo.AttNameValueVO;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.AttributeValueVO;
import com.egeo.components.product.vo.BrandVO;
import com.egeo.components.product.vo.CategoryAttValueVO;
import com.egeo.components.product.vo.PictureVO;
import com.egeo.components.product.vo.ProductDescriptionVO;
import com.egeo.components.product.vo.ProductPictureVO;
import com.egeo.components.product.vo.ProductVO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProductManage {

	PageResult<ProductDTO> findPage(Pagination page, ProductVO productVO, List<String> nameList);

	String saveProduct(ProductVO productVO, ProductDescriptionVO productDescriptionVO, PictureVO pictureVO,
			BrandVO brandVO, List<AttNameValueVO> lists,
			List<AttName> apecification ,List<AttNameValueVO> parameterAtts);

	ProductVO productById(ProductVO productVO, CategoryAttValueVO categoryAttValueVO,
			ProductDescriptionVO productDescriptionVO, PictureVO pictureVO, ProductPictureVO productPictureVO,
			AttributeValueVO attributeValueVO, AttributeNameVO attributeNameVO);

	String updateProduct(ProductVO productVO, ProductDescriptionVO productDescriptionVO, PictureVO pictureVO,
			BrandVO brandVO, List<AttNameValueVO> lists,List<AttNameValueVO> parameterAtts,Long showProductAttNameId);
	String deleteProduct(ProductVO productVO);

	Long audit(ProductVO productVO);

	List<ProductVO> findAll(ProductVO productVO);

	String passAllAudit(String ids, Long userId, String userName, String ip, String mac,Long platformId);

	boolean updateStatus(ProductVO productVO, Long userId, String userName, String ip, String mac);

	PageResult<StandardProductUnitDTO> productByCategoryId(Pagination page, ProductVO productVO, Long categoryId);
	PageResult<Map<String, Object>> productByCategoryIdQuick(Pagination page, ProductVO productVO, Long categoryId);

	Long categoryIdByProductId(Long productId);

	List<String> idToName(List<Long> categoryPId);

	String idTobrandName(Long brandId);

	ProductVO productByProductId(ProductVO productVO, CategoryAttValueVO categoryAttValueVO,
			ProductDescriptionVO productDescriptionVO, PictureVO pictureVO, ProductPictureVO productPictureVO,
			AttributeValueVO attributeValueVO, AttributeNameVO attributeNameVO);

	ProductVO ProductById(ProductVO productVO);

	/**
	 * 设置是否启用
	 * 
	 * @param productVO
	 * @return
	 */
	int updateAvailable(ProductVO productVO, String ids);

	int updateProductReferLink(ProductVO vo);

	boolean checkProductPriceDetail(ProductVO vo);

}
