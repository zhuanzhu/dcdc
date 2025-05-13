package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ProductAttValuePO;

import java.util.List;

public interface ProductAttValueWriteManage {

    Long saveProductAttValueWithTx(ProductAttValuePO po);

    String deleteByProductAttNameIdWithTx(ProductAttValuePO po);

    String deleteByMuchProductAttNameIdWithTx(String productAttNameIds);
    /**
	 * 根据产品属性值id删除产品属性值的关系
	 * @param productAttValueVO
	 * @return
	 */
	int deleteById(ProductAttValuePO po);
	/**
	 * 根据spu规格属性值草稿id保存spu规格图片
	 * @param productAttValueId
	 * @param pictureUrl
	 * @return
	 */
	boolean saveImgByProductAttValueId(Long productAttValueId, String pictureUrl);
	/**
	 * 根据spu规格属性草稿id删除spu规格图片
	 * @param productAttValueId
	 * @param pictureUrl
	 * @return
	 */
	boolean delImgByProductAttNameId(Long productAttNameId);
	/**
	 * 根据属性属性值和spuid删除规格信息
	 * @param productId
	 * @param attributeNameId
	 * @param attributeValueId
	 * @return
	 */
	boolean delByAttIdAndSpuIdWithTx(Long productId, Long attributeNameId, Long attributeValueId,Long productAttValueId);

    void saveProductAttValue(List<ProductAttValuePO> productAttValuePOList);
}
	