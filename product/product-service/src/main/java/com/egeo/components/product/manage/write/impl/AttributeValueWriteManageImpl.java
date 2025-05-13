package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.AttributeValueWriteManage;
import com.egeo.components.product.dao.write.AttributeValueWriteDAO;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class AttributeValueWriteManageImpl implements AttributeValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AttributeValueWriteDAO attributeValueWriteDAO;
        @Override
        public Long saveAttributeValue(AttributeValuePO po) {
            attributeValueWriteDAO.insert(po);
            if(po.getId() != null){
                return po.getId();
            }else{
                throw new BusinessException(BusinessExceptionConstant.NO_SAVE_ATTRIBUTEVALUE, "添加节点属性id与属性值数据失败");
            }
            
        }
		@Override
		public Integer deleteByIdWithTx(AttributeValuePO po) {
			return attributeValueWriteDAO.delete(po);
		}
		@Override
		public Long updateAttributeValueWithTx(AttributeValuePO po) {
			attributeValueWriteDAO.update(po);
			return po.getId();
		}
		/**
		 * 根据属性id删除属性值信息(ps：因为主键唯一无须平台id)
		 * @param id
		 * @return
		 */
		@Override
		public int deleteByAttributeNameIdWithTx(Long attributeNameId) {
			AttributeValuePO po = new AttributeValuePO();
			po.setAttributeNameId(attributeNameId);
			return attributeValueWriteDAO.deleteByPara(po);
		}

	@Override
	public void saveAttValue(List<AttributeValuePO> attributeValuePOList) {
			try{
		attributeValueWriteDAO.saveAttValue(attributeValuePOList);
			}catch (Exception e){
				logger.error("saveAttValue失败,e:"+e.getMessage());
			}
	}

	@Override
	public void updateAttributeValueList(List<AttributeValuePO> attributeValuePOList) {
		try{
			attributeValueWriteDAO.updateAttributeValueList(attributeValuePOList);
		}catch (Exception e){
			logger.error("saveAttValue失败,e:"+e.getMessage());
		}
	}
}
	