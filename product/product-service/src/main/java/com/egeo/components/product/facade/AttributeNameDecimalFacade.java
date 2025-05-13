package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.AttributeNameDecimalReadService;
import com.egeo.components.product.service.write.AttributeNameDecimalWriteService;
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class AttributeNameDecimalFacade {
	
	@Resource
	private AttributeNameDecimalReadService attributeNameDecimalReadService;
	
	@Resource
	private AttributeNameDecimalWriteService attributeNameDecimalWriteService;
	
	
	public AttributeNameDecimalDTO findAttributeNameDecimalById(AttributeNameDecimalDTO dto){
		
		return attributeNameDecimalReadService.findAttributeNameDecimalById(dto);
	}

	public PageResult<AttributeNameDecimalDTO> findAttributeNameDecimalOfPage(AttributeNameDecimalDTO dto,Pagination page){
		
		return attributeNameDecimalReadService.findAttributeNameDecimalOfPage(dto, page);
		
	}

	public List<AttributeNameDecimalDTO> findAttributeNameDecimalAll(AttributeNameDecimalDTO dto){
		
		return attributeNameDecimalReadService.findAttributeNameDecimalAll(dto);
		
	}

	public Long insertAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto){
		
		return attributeNameDecimalWriteService.insertAttributeNameDecimalWithTx(dto);
	}

	public int updateAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto){
		
		return attributeNameDecimalWriteService.updateAttributeNameDecimalWithTx(dto);
	}

	public int deleteAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto){
		
		return attributeNameDecimalWriteService.deleteAttributeNameDecimalWithTx(dto);
		
	}

}
	