package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.AttValuePO;
import com.egeo.components.product.po.AttributeNamePO;

public interface AttributeNameWriteManage {

    Long saveAttributeNameWithTx(AttributeNamePO po,List<AttValuePO> lists,String begin, String finish);

    Long updateAttributeNameWithTx(AttributeNamePO po,List<AttValuePO> lists,String begin, String finish);

    Long deleteByIdWithTx(AttributeNamePO po);
    /**
     * 根据属性id修改属性信息
     * @param dto
     * @return
     */
	int updateAttributeNameWithTx(AttributeNamePO po);
	
	Long saveAttributeNameWithTx(AttributeNamePO po);
}
	