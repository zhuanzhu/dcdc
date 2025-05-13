package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.vo.AttValueCustomReqVO;
import com.egeo.components.product.vo.ProductAttValueVO;
import com.egeo.components.product.dto.ProductAttValueDTO;

public interface ProductAttValueManage {

    Long saveProductAttValue(ProductAttValueVO productAttValueVO);

    List<ProductAttValueDTO> findAll(ProductAttValueVO productAttValueVO);

    String deleteByProductAttNameId(ProductAttValueVO productAttValueVO);

    String deleteByMuchProductAttNameId(String productAttNameIds);
    /**
     * 根据产品id和属性id保存产品属性值信息
     * @param productId
     * @param attnameId
     * @return
     */
	Long saveProductAttValueByProductIdAndAttnameId(Long productId,List<ProductAttValueVO> productAttValueVOList,Long platformId);
	/**
	 * 根据产品属性值id删除产品属性值的关系
	 * @param productAttValueVO
	 * @return
	 */
	int deleteById(ProductAttValueVO productAttValueVO, Long productId);
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

	ProductAttValueDTO findAttValueCustomBySpuId(AttValueCustomReqVO vo);
}
