package com.egeo.components.product.business.impl;


import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.vo.AttValueCustomReqVO;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductAttValueManage;
import com.egeo.components.product.converter.ProductAttValueConverter;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.facade.AttributeNameFacade;
import com.egeo.components.product.facade.AttributeValueFacade;
import com.egeo.components.product.facade.ProductAttNameFacade;
import com.egeo.components.product.facade.ProductAttValueFacade;
import com.egeo.components.product.facade.ProductFacade;
import com.egeo.components.product.vo.ProductAttValueVO;
import org.springframework.util.CollectionUtils;

@Service("productAttValue")
public class ProductAttValueManageImpl implements ProductAttValueManage{


	@Resource(name="productAttValueFacade")
	private ProductAttValueFacade productAttValueFacade;

	@Resource(name="productAttNameFacade")
	private ProductAttNameFacade productAttNameFacade;

	@Resource(name="productFacade")
	private ProductFacade productFacade;

	@Resource(name="attributeNameFacade")
	private AttributeNameFacade attributeNameFacade;

	@Resource(name="attributeValueFacade")
	private AttributeValueFacade attributeValueFacade;

        @Override
        public Long saveProductAttValue(ProductAttValueVO productAttValueVO) {

            return productAttValueFacade.saveProductAttValue(ProductAttValueConverter.toDTO(productAttValueVO));
        }

        @Override
        public List<ProductAttValueDTO> findAll(ProductAttValueVO productAttValueVO) {

            return productAttValueFacade.findAll(ProductAttValueConverter.toDTO(productAttValueVO));
        }

        @Override
        public String deleteByProductAttNameId(ProductAttValueVO productAttValueVO) {

            return productAttValueFacade.deleteByProductAttNameId(ProductAttValueConverter.toDTO(productAttValueVO));
        }

        @Override
        public String deleteByMuchProductAttNameId(String productAttNameIds) {

            return productAttValueFacade.deleteByMuchProductAttNameId(productAttNameIds);
        }
        /**
         * 根据产品id和属性id保存产品属性值信息
         * @param productId
         * @param attnameId
         * @return
         */
		@Override
		public Long saveProductAttValueByProductIdAndAttnameId(Long productId, List<ProductAttValueVO> productAttValueVOList,Long platformId) {

			return productAttValueFacade.saveProductAttValueByProductIdAndAttnameId(productId, ProductAttValueConverter.toDTOs(productAttValueVOList),platformId);
		}
		/**
		 * 根据产品属性值id删除产品属性值的关系
		 * @param productAttValueVO
		 * @return
		 */
		@Override
		public int deleteById(ProductAttValueVO productAttValueVO, Long productId) {
			// TODO Auto-generated method stub
			return productAttValueFacade.deleteById(ProductAttValueConverter.toDTO(productAttValueVO), productId);
		}
		/**
		 * 根据spu规格属性值草稿id保存spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		@Override
		public boolean saveImgByProductAttValueId(Long productAttValueId, String pictureUrl) {
			// TODO Auto-generated method stub
			return productAttValueFacade.saveImgByProductAttValueId(productAttValueId, pictureUrl);
		}
		/**
		 * 根据spu规格属性草稿id删除spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		@Override
		public boolean delImgByProductAttNameId(Long productAttNameId) {
			// TODO Auto-generated method stub
			return productAttValueFacade.delImgByProductAttNameId(productAttNameId);
		}
		/**
		 * 根据属性属性值和spuid删除规格信息
		 * @param productId
		 * @param attributeNameId
		 * @param attributeValueId
		 * @return
		 */
		@Override
		public boolean delByAttIdAndSpuIdWithTx(Long productId, Long attributeNameId, Long attributeValueId,Long productAttValueId) {
			// TODO Auto-generated method stub
			return productAttValueFacade.delByAttIdAndSpuIdWithTx(productId, attributeNameId, attributeValueId,productAttValueId);
		}

	@Override
	public ProductAttValueDTO findAttValueCustomBySpuId(AttValueCustomReqVO vo){
		if(EmptyUtil.isEmpty(vo) || EmptyUtil.isEmpty(vo.getStandardProductUnitId())){
			return null;
		}
		if(EmptyUtil.isEmpty(vo.getAttrName())){
			vo.setAttrName("产品编码");
		}
		AttributeNameDTO attributeNameDTO = attributeNameFacade.findByAttName(vo.getAttrName());
		if(attributeNameDTO == null){
			return null;
		}
		ProductAttNameDTO queryDTO = new ProductAttNameDTO();
		queryDTO.setAttNameId(attributeNameDTO.getId());
		queryDTO.setProductId(vo.getStandardProductUnitId());
		queryDTO.setType(3);
		List<ProductAttNameDTO> list = productAttNameFacade.findAll(queryDTO);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		ProductAttNameDTO productAttNameDTO = list.get(0);
		ProductAttValueDTO queryProductAttValueDTO = new ProductAttValueDTO();
		queryProductAttValueDTO.setProductAttNameId(productAttNameDTO.getId());
		List<ProductAttValueDTO> rtList = productAttValueFacade.findAll(queryProductAttValueDTO);
		if(CollectionUtils.isEmpty(rtList)){
			return null;
		}
		return rtList.get(0);
	}

}
