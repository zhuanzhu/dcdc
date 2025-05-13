package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttributeValueWriteDAO extends BaseWriteDAO<AttributeValuePO> {
    void saveAttValue(@Param("poList")List<AttributeValuePO> attributeValuePOList);

    void updateAttributeValueList(@Param("poList")List<AttributeValuePO> attributeValuePOList);
}
	