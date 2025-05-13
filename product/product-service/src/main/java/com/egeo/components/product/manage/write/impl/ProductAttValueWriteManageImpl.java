package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductAttValueWriteManage;
import com.egeo.components.product.dao.read.StandardProductUnitAttValueReadDAO;
import com.egeo.components.product.dao.write.ProductAttValueWriteDAO;
import com.egeo.components.product.po.ProductAttValuePO;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

import java.util.List;

@Service
public class ProductAttValueWriteManageImpl implements ProductAttValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductAttValueWriteDAO productAttValueWriteDAO;
	
	@Autowired
	private StandardProductUnitAttValueReadDAO standardProductUnitAttValueReadDAO;
	
        @Override
        public Long saveProductAttValueWithTx(ProductAttValuePO po) {
            productAttValueWriteDAO.insert(po);
            if(po.getId() != null){
            	return po.getId();
            }else{
            	throw new BusinessException("新增产品属性值失败");
            }
            
        }

        @Override
        public String deleteByProductAttNameIdWithTx(ProductAttValuePO po) {
            
            return productAttValueWriteDAO.deleteByProductAttNameId(po)+"";
        }

        @Override
        public String deleteByMuchProductAttNameIdWithTx(String productAttNameIds) {
            
            return productAttValueWriteDAO.deleteByMuchProductAttNameId(productAttNameIds)+"";
        }
        /**
		 * 根据产品属性值id删除产品属性值的关系
		 * @param productAttValueVO
		 * @return
		 */
		@Override
		public int deleteById(ProductAttValuePO po) {
			// TODO Auto-generated method stub
			return productAttValueWriteDAO.delete(po);
		}
		/**
		 * 根据spu规格属性值草稿id保存spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		@Override
		public boolean saveImgByProductAttValueId(Long productAttValueId, String pictureUrl) {
			ProductAttValuePO productAttValuePO = new ProductAttValuePO();
			productAttValuePO.setId(productAttValueId);
			productAttValuePO.setPictureUrl(pictureUrl);
			int i = productAttValueWriteDAO.update(productAttValuePO);
			if(i != 0){
				return true;
			}
			return false;
		}
		/**
		 * 根据spu规格属性草稿id删除spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		@Override
		public boolean delImgByProductAttNameId(Long productAttNameId) {
			int i = productAttValueWriteDAO.delImgByProductAttNameId(productAttNameId);
			if(i != 0){
				return true;
			}
			return false;
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
			boolean isTrue = false;
			//根据spuid属性id属性值id查询spu规格
			StandardProductUnitAttValuePO standardProductUnitAttValuePO = standardProductUnitAttValueReadDAO.findBypuIdAttNameIdAttValueId(productId,attributeNameId,attributeValueId);
			if(EmptyUtil.isNotEmpty(standardProductUnitAttValuePO)){
				throw new BusinessException("spu规格属性以生成，不能移除");
			}
			ProductAttValuePO productAttValuePO = new ProductAttValuePO();
			productAttValuePO.setId(productAttValueId);
			productAttValueWriteDAO.delete(productAttValuePO);
			isTrue = true;
			return isTrue;
		}

	@Override
	public void saveProductAttValue(List<ProductAttValuePO> productAttValuePOList) {
			try{
		productAttValueWriteDAO.saveProductAttValue(productAttValuePOList);
			}catch (Exception e){
				logger.error("saveProductAttValue失败,e:"+e.getMessage());
			}
	}
}
	