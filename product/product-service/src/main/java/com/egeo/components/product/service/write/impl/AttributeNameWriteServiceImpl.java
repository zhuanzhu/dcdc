package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.AttributeNameWriteService;
import com.egeo.components.product.manage.write.AttributeNameWriteManage;
import com.egeo.components.product.converter.AttributeNameConverter;
import com.egeo.components.product.converter.AttributeValueConverter;
import com.egeo.components.product.dto.AttValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.po.AttValuePO;
import com.egeo.components.product.po.AttributeNamePO;


@Service("attributeNameWriteService")
public class AttributeNameWriteServiceImpl  implements AttributeNameWriteService {
	@Autowired
	private AttributeNameWriteManage attributeNameWriteManage;

    @Override
    public Long saveAttributeNameWithTx(AttributeNameDTO dto,List<AttValueDTO> lists,String begin, String finish) {
    	List<AttValuePO> attValuePOs = new ArrayList<>();
    	for (AttValueDTO attValueDTO : lists) {
    		AttValuePO attValuePO = new AttValuePO();
    		attValuePO.setId(attValueDTO.getId());
    		attValuePO.setParentId(attValueDTO.getParentId());
    		attValuePO.setAttValue(attValueDTO.getAttValue());
    		attValuePO.setSortValue(attValueDTO.getSortValue());
    		attValuePO.setSpecificationCode(attValueDTO.getSpecificationCode());
    		attValuePOs.add(attValuePO);
		}
    	AttributeNamePO attributeNamePO = AttributeNameConverter.toPO(dto);
    	attributeNamePO.setUnit(dto.getUnit());
    	attributeNamePO.setBeginDecimal(dto.getBeginDecimal());
    	attributeNamePO.setFinishDecimal(dto.getFinishDecimal());
    	attributeNamePO.setAttValueCustom(dto.getAttValueCustom());
    	attributeNamePO.setAttributeValueList(AttributeValueConverter.toPO(dto.getAttributeValueList()));
    	attributeNamePO.setReminder(dto.getReminder());
    	attributeNamePO.setIsRequired(dto.getIsRequired());
    	attributeNamePO.setImportHint(dto.getImportHint());

        return attributeNameWriteManage.saveAttributeNameWithTx(attributeNamePO,attValuePOs,begin,finish);
    }

    @Override
    public Long updateAttributeNameWithTx(AttributeNameDTO dto,List<AttValueDTO> lists,String begin, String finish) {
    	List<AttValuePO> attValuePOs = new ArrayList<>();
    	for (AttValueDTO attValueDTO : lists) {
    		AttValuePO attValuePO = new AttValuePO();
    		attValuePO.setId(attValueDTO.getId());
    		attValuePO.setParentId(attValueDTO.getParentId());
    		attValuePO.setAttValue(attValueDTO.getAttValue());
    		attValuePO.setSortValue(attValueDTO.getSortValue());
    		attValuePO.setSpecificationCode(attValueDTO.getSpecificationCode());
    		attValuePOs.add(attValuePO);
		}
        AttributeNamePO attributeNamePO = AttributeNameConverter.toPO(dto);
        attributeNamePO.setUnit(dto.getUnit());
    	attributeNamePO.setBeginDecimal(dto.getBeginDecimal());
    	attributeNamePO.setFinishDecimal(dto.getFinishDecimal());
    	attributeNamePO.setAttValueCustom(dto.getAttValueCustom());
    	attributeNamePO.setAttributeValueList(AttributeValueConverter.toPO(dto.getAttributeValueList()));
    	attributeNamePO.setReminder(dto.getReminder());
    	attributeNamePO.setIsRequired(dto.getIsRequired());
    	attributeNamePO.setImportHint(dto.getImportHint());

        
        return attributeNameWriteManage.updateAttributeNameWithTx(attributeNamePO,attValuePOs,begin,finish);
    }

    @Override
    public Long deleteByIdWithTx(AttributeNameDTO dto) {
        return attributeNameWriteManage.deleteByIdWithTx(AttributeNameConverter.toPO(dto));
    }
    /**
     * 根据属性id修改属性信息
     * @param dto
     * @return
     */
	@Override
	public int updateAttributeNameWithTx(AttributeNameDTO dto) {
		// TODO Auto-generated method stub
		return attributeNameWriteManage.updateAttributeNameWithTx(AttributeNameConverter.toPO(dto));
	}

	@Override
	public Long saveAttributeNameWithTx(AttributeNameDTO dto) {
		return attributeNameWriteManage.saveAttributeNameWithTx(AttributeNameConverter.toPO(dto));
	}
}
	