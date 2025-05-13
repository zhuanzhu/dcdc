package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.AttValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;

public interface AttributeNameWriteService {

    Long saveAttributeNameWithTx(AttributeNameDTO dto,List<AttValueDTO> lists,String begin, String finish);

    Long updateAttributeNameWithTx(AttributeNameDTO dto,List<AttValueDTO> lists,String begin, String finish);

    Long deleteByIdWithTx(AttributeNameDTO dto);
    /**
     * 根据属性id修改属性信息
     * @param dto
     * @return
     */
    int  updateAttributeNameWithTx(AttributeNameDTO dto);
    
    Long saveAttributeNameWithTx(AttributeNameDTO dto);
}
	