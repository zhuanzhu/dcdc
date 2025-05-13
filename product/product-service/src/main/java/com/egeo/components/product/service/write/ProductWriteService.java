package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.AttNameValueDTO;
import com.egeo.components.product.dto.BrandDTO;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.ProductDescriptionDTO;

public interface ProductWriteService {

    Long saveProductWithTx(ProductDTO dto,ProductDescriptionDTO productDescriptionDTO,PictureDTO pictureDTO,BrandDTO brandDTO,List<AttNameValueDTO> lists);

    String updateProductWithTx(ProductDTO dto, ProductDescriptionDTO dto2, PictureDTO dto3,BrandDTO brandDTO,List<AttNameValueDTO> lists,Long showProductAttNameId);

    String deleteProductWithTx(ProductDTO dto);

    Long auditWithTx(ProductDTO dto);

    int updateWithTx(ProductDTO productDTO2);

    int updateStatusWithTx(ProductDTO dto,Long userId,String userName,String ip,String mac);
	/**
	 * 保存spu草稿信息
	 * @param id
	 * @return
	 */
	Long insertProductWithTx(ProductDTO productDTO, ProductDescriptionDTO productDescriptionDTO);
	/**
	 * 设置是否启用
	 * @param productVO
	 * @return
	 */
	int updateAvailableWithTx(ProductDTO dto);
	/**
	 * 同步spu信息和记录信息
	 * @param productId
	 * @return
	 */
	int synchronizationStandardProductUnitWithTx(Long productId,Long userId,String userName,String ip,String mac);
	/**
	 * 删除所有点击添加规格属性没有点下一步所创建的空spu草稿信息
	 * @return
	 */
	int delByNullProductWithTx();
	
	public List<Long> assembleSku(Long productId,String standardProductUnitName,String productSerialNumber);

    void saveProductList(List<Long> productIdList, List<String> spuSerialNumberList, List<String> productCategoryList, List<Long> catgoryTreeNodeIdList, List<String> nameList);

    void updateProductList(List<Long> productIdList, List<String> nameList);

	void cleanLink(ProductDTO productDTO2);
}
	