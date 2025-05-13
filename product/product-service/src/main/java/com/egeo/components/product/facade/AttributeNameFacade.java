package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.product.dto.AttValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.service.read.AttributeNameReadService;
import com.egeo.components.product.service.read.AttributeValueReadService;
import com.egeo.components.product.service.read.CategoryAttNameReadService;
import com.egeo.components.product.service.write.AttributeNameDecimalWriteService;
import com.egeo.components.product.service.write.AttributeNameWriteService;
import com.egeo.components.product.service.write.AttributeValueWriteService;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class AttributeNameFacade {

	@Resource
	private AttributeNameReadService attributeNameReadService;

	@Resource
    private AttributeNameWriteService attributeNameWriteService;

	@Resource
    private AttributeValueWriteService attributeValueWriteService;

	@Resource
    private AttributeValueReadService attributeValueReadService;

	@Resource
    private CategoryAttNameReadService categoryAttNameReadService;

	@Resource
	private AttributeNameDecimalWriteService attributeNameDecimalWriteService;

        public AttributeNameVO findById(AttributeNameDTO dto) {
            AttributeNameVO attributeNameVO = new AttributeNameVO();
            //根据属性id查询属性信息
            AttributeNameDTO attributeNameDTO = attributeNameReadService.findById(dto);

            attributeNameVO.setId(attributeNameDTO.getId());
            attributeNameVO.setName(attributeNameDTO.getName());
            attributeNameVO.setMode(attributeNameDTO.getMode());
            attributeNameVO.setSpecificationProperty(attributeNameDTO.getSpecificationProperty());
            attributeNameVO.setParameterProperty(attributeNameDTO.getParameterProperty());
            attributeNameVO.setReminder(attributeNameDTO.getReminder());
            attributeNameVO.setImportHint(attributeNameDTO.getImportHint());
            attributeNameVO.setIsRequired(attributeNameDTO.getIsRequired());

            //根据属性id查询属性值信息
            AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
            attributeValueDTO.setAttributeNameId(dto.getId());
            List<AttributeValueDTO> list = attributeValueReadService.findAll(attributeValueDTO);
            List<AttValueDTO> lists = new ArrayList<AttValueDTO>();
            for (AttributeValueDTO attributeValueDTO2 : list) {
            	AttValueDTO attValue = new AttValueDTO();
                attValue.setId(attributeValueDTO2.getId());
                attValue.setParentId(attributeValueDTO2.getParentId());
                attValue.setAttValue(attributeValueDTO2.getValue());
                attValue.setSortValue(attributeValueDTO2.getSortValue());
                attValue.setSpecificationCode(attributeValueDTO2.getSpecificationCode());
                lists.add(attValue);
            }
            attributeNameVO.setLists(lists);
            return attributeNameVO;
        }

        public List<AttributeNameDTO> findAll(AttributeNameDTO dto) {
            return attributeNameReadService.findAll(dto);
        }

        public PageResult<AttributeNameDTO> findPageAttributeName(Pagination page, AttributeNameDTO dto) {
            return attributeNameReadService.findPageAttributeName(page, dto);
        }

        public Long saveAttributeName(AttributeNameDTO dto,List<AttValueDTO> lists,String begin, String finish) {
        	AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
        	attributeNameDTO.setName(dto.getName());
        	List<AttributeNameDTO> list = attributeNameReadService.findAll(attributeNameDTO);
        	if(list.size() > 0){
        		throw new BusinessException(BusinessExceptionConstant.ATT_NAME_EXIST, "该属性名称已存在！");
        	}

            return attributeNameWriteService.saveAttributeNameWithTx(dto,lists,begin,finish);
        }

        public Long updateAttributeName(AttributeNameDTO dto,List<AttValueDTO> lists,String begin, String finish) {
        	//根据属性名称查询属性信息
        	AttributeNameDTO attributeNameDTO = attributeNameReadService.findByAttName(dto.getName());
        	if(EmptyUtil.isNotEmpty(attributeNameDTO)){
        		if(!dto.getId().equals(attributeNameDTO.getId())){
        			throw new BusinessException(BusinessExceptionConstant.ATT_NAME_EXIST, "该属性名称已存在！");
        		}
        	}

            return attributeNameWriteService.updateAttributeNameWithTx(dto,lists,begin,finish);
        }

        public Long deleteById(AttributeNameDTO dto) {
        	//根据属性id查询属性值信息
        	Long attributeNameId = attributeNameWriteService.deleteByIdWithTx(dto);
        	//根据属性id删除属性值信息(ps：因为主键唯一无须平台id)
        	attributeValueWriteService.deleteByAttributeNameIdWithTx(dto.getId());
            return attributeNameId;
        }

		public AttributeNameDTO findByAttributeNameId(AttributeNameDTO attributeNameDTO) {
			//根据属性id查询属性信息
			return attributeNameReadService.findById(attributeNameDTO);
		}

		public List<AttributeNameDTO> findByIds(String ids,Integer specificationProperty,Integer parameterProperty,String name) {
			return attributeNameReadService.findByIds(ids,specificationProperty,parameterProperty,name);
		}

		public List<AttributeNameDTO> findAllByName(String name, String ids) {
			return attributeNameReadService.findAllByName(name, ids);
		}
		/**
		 * 根据类目id查询属性信息
		 * @param categoryId
		 * @return
		 */
		public List<AttributeNameDTO> findByCategoryId(Long categoryId) {
			// TODO Auto-generated method stub
			return attributeNameReadService.findByCategoryId(categoryId);
		}
		/**
		 * 根据spu草稿id查询属性和属性值信息
		 * @param productId
		 * @return
		 */
		public List<AttributeNameDTO> findByProductId(Long productId) {
			// TODO Auto-generated method stub
			return attributeNameReadService.findByProductId(productId);
		}

		public void saveAttributeName(AttributeNameDTO attributeNameDTO) {
			attributeNameWriteService.saveAttributeNameWithTx(attributeNameDTO);
		}

		public AttributeNameDTO findByAttName(String name){
			AttributeNameDTO attributeNameDTO = attributeNameReadService.findByAttName(name);
			return attributeNameDTO;
		}

}
