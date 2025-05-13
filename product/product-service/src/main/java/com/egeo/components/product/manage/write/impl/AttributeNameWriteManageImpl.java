package com.egeo.components.product.manage.write.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.AttributeNameWriteManage;
import com.egeo.components.product.dao.read.AttributeValueReadDAO;
import com.egeo.components.product.dao.write.AttributeNameDecimalWriteDAO;
import com.egeo.components.product.dao.write.AttributeNameWriteDAO;
import com.egeo.components.product.dao.write.AttributeValueWriteDAO;
import com.egeo.components.product.po.AttValuePO;
import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;

@Service
public class  AttributeNameWriteManageImpl implements AttributeNameWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AttributeNameWriteDAO attributeNameWriteDAO;

	@Autowired
	private AttributeValueReadDAO attributeValueReadDAO;

	@Autowired
	private AttributeValueWriteDAO attributeValueWriteDAO;

	@Autowired
	private AttributeNameDecimalWriteDAO attributeNameDecimalWriteDAO;

	@Override
	public Long saveAttributeNameWithTx(AttributeNamePO po, List<AttValuePO> lists, String begin, String finish) {
		attributeNameWriteDAO.insert(po);
		if (po.getId() != null) {
			if (po.getMode() == 1 || po.getMode() == 3) {
				for (AttValuePO attValue : lists) {

					AttributeValuePO attributeValue = new AttributeValuePO();
					if (EmptyUtil.isEmpty(attValue.getAttValue())) {
						// 根据属性名称id删除属性信息
						/*
						 * AttributeNamePO attributeNameDTO2 = new
						 * AttributeNamePO();
						 * attributeNameDTO2.setId(po.getId());
						 * attributeNameWriteDAO.delete(attributeNameDTO2);
						 * //根据属性id删除属性值信息(ps：因为主键唯一无须平台id) AttributeValuePO
						 * attributeValuePO = new AttributeValuePO();
						 * attributeValuePO.setAttributeNameId(po.getId());
						 * attributeValueWriteDAO.deleteByPara(attributeValuePO)
						 * ;
						 */
						throw new BusinessException("属性值名称不能为空");
					}
					if(EmptyUtil.isBlank(attValue.getSpecificationCode())) {
						throw new BusinessException(BusinessExceptionConstant.SPECIFICATIONCODE_VALUE_NO_NULL, "属性值规格码不能为空！");
					}
					if (attValue.getAttValue().length() > 30) {
						throw new BusinessException("字符长度不得超过30位");
					}
					if (EmptyUtil.isEmpty(attValue.getSortValue())) {
						// 根据属性id查询属性值排序最大的值
						Integer maxSortValue = attributeValueReadDAO.maxSortValue(po.getId());
						attValue.setSortValue(maxSortValue + 1);
					} 
					/*if (EmptyUtil.isEmpty(attValue.getSortValue())) {
						// 根据属性id查询属性值排序最大的值
						Integer maxSortValue = attributeValueReadDAO.maxSortValue(po.getId());
						attributeValue.setSortValue(maxSortValue + 1);
					} else {
						attributeValue.setSortValue(attValue.getSortValue());
					}*/
					attributeValue.setAttributeNameId(po.getId());
					attributeValue.setValue(attValue.getAttValue());

					List<AttributeValuePO> list2 = attributeValueReadDAO.findAll(attributeValue,null);
					if (list2.size() > 0) {
						logger.info("属性值名称重复:"+attributeValue);
						throw new BusinessException(BusinessExceptionConstant.ATT_VALUE_EXIST, "属性值名称重复！");
					}
					AttributeValuePO attributeValuePO = new AttributeValuePO();
					attributeValuePO.setParentId(attValue.getParentId());
					attributeValuePO.setAttributeNameId(po.getId());
					attributeValuePO.setSortValue(attValue.getSortValue());
					attributeValuePO.setValue(attValue.getAttValue());
					attributeValuePO.setSpecificationCode(attValue.getSpecificationCode());
					attributeValueWriteDAO.insert(attributeValuePO);
				}
			} else if (po.getMode() == 4) {
				AttributeNameDecimalPO attributeNameDecimalPO = new AttributeNameDecimalPO();
				attributeNameDecimalPO.setAttributeNameId(po.getId());
				if (!StringUtils.regularPositive(begin) || !StringUtils.regularPositive(finish)
						|| !StringUtils.isNotFigure(begin) || !StringUtils.isNotFigure(finish)) {
					throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入0-9999数字");
				} else {
					BigDecimal beginBigDecimal = new BigDecimal(begin);
					BigDecimal finishBigDecimal = new BigDecimal(finish);
					if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
						throw new BusinessException("属性范围终值不能小于起始值");
					}
					if (EmptyUtil.isNotEmpty(begin)) {
						attributeNameDecimalPO.setBeginDecimal(beginBigDecimal);
					}
					if (EmptyUtil.isNotEmpty(finish)) {
						attributeNameDecimalPO.setFinishDecimal(finishBigDecimal);
					}
					attributeNameDecimalPO.setAttributeNameId(po.getId());
				}
				attributeNameDecimalWriteDAO.insert(attributeNameDecimalPO);
			} else if (po.getMode() == 2) {
				AttributeNameDecimalPO attributeNameDecimalDTO = new AttributeNameDecimalPO();
				attributeNameDecimalDTO.setAttributeNameId(po.getId());
				if (!StringUtils.regularPositive(begin) || !StringUtils.regularPositive(finish)
						|| !StringUtils.isNotFigure(begin) || !StringUtils.isNotFigure(finish) || begin.length() > 4
						|| finish.length() > 4) {
					throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入0-9999数字");
				} else {
					BigDecimal beginBigDecimal = new BigDecimal(begin);
					BigDecimal finishBigDecimal = new BigDecimal(finish);
					if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
						throw new BusinessException("属性范围终值不能小于起始值");
					}
					if (EmptyUtil.isNotEmpty(begin)) {
						attributeNameDecimalDTO.setBeginDecimal(beginBigDecimal);
					}
					if (EmptyUtil.isNotEmpty(finish)) {
						attributeNameDecimalDTO.setFinishDecimal(finishBigDecimal);
					}
					attributeNameDecimalDTO.setAttributeNameId(po.getId());
				}
				attributeNameDecimalWriteDAO.insert(attributeNameDecimalDTO);
			} else if (po.getMode() == 5) {
				AttributeNameDecimalPO attributeNameDecimalDTO = new AttributeNameDecimalPO();
				attributeNameDecimalDTO.setAttributeNameId(po.getId());
				attributeNameDecimalDTO.setUnit(po.getUnit());
				if (!StringUtils.regularPositive(begin) || !StringUtils.regularPositive(finish)
						|| !StringUtils.isNotFigure(begin) || !StringUtils.isNotFigure(finish) || begin.length() > 4
						|| finish.length() > 4) {
					throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入0-9999数字");
				} else {
					BigDecimal beginBigDecimal = new BigDecimal(begin);
					BigDecimal finishBigDecimal = new BigDecimal(finish);
					if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
						throw new BusinessException("属性范围终值不能小于起始值");
					}
					if (EmptyUtil.isNotEmpty(begin)) {
						attributeNameDecimalDTO.setBeginDecimal(beginBigDecimal);
					}
					if (EmptyUtil.isNotEmpty(finish)) {
						attributeNameDecimalDTO.setFinishDecimal(finishBigDecimal);
					}
					attributeNameDecimalDTO.setAttributeNameId(po.getId());
				}
				attributeNameDecimalDTO.setImportHint(po.getImportHint());
				attributeNameDecimalWriteDAO.insert(attributeNameDecimalDTO);
			} else if (po.getMode() == 6) {
				AttributeNameDecimalPO attributeNameDecimalPO = new AttributeNameDecimalPO();
				attributeNameDecimalPO.setAttributeNameId(po.getId());
				if (po.getMode() == 6) {
					attributeNameDecimalPO.setUnit(po.getUnit());
					attributeNameDecimalPO.setImportHint(po.getImportHint());
				}
				if (EmptyUtil.isEmpty(begin) || EmptyUtil.isEmpty(finish) || !StringUtils.isNotFigure(begin)
						|| !StringUtils.isNotFigure(finish)
						|| new BigDecimal(begin).doubleValue() > BigDecimal.valueOf(9999.99).doubleValue()
						|| new BigDecimal(finish).doubleValue() > BigDecimal.valueOf(9999.99).doubleValue()
						|| BigDecimal.valueOf(-9999.99).doubleValue() > BigDecimal.valueOf(Long.valueOf(finish)).doubleValue()
						|| BigDecimal.valueOf(-9999.99).doubleValue() > BigDecimal.valueOf(Long.valueOf(begin)).doubleValue()) {
					throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入-9999.99-9999.99数字");
				} else {
					BigDecimal beginBigDecimal = new BigDecimal(begin);
					BigDecimal finishBigDecimal = new BigDecimal(finish);
					if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
						throw new BusinessException("属性范围终值不能小于起始值");
					}
					if (EmptyUtil.isNotEmpty(begin)) {
						attributeNameDecimalPO.setBeginDecimal(beginBigDecimal);
					}
					if (EmptyUtil.isNotEmpty(finish)) {
						attributeNameDecimalPO.setFinishDecimal(finishBigDecimal);
					}
					attributeNameDecimalPO.setAttributeNameId(po.getId());
				}
				attributeNameDecimalWriteDAO.insert(attributeNameDecimalPO);
			}
			return po.getId();
		} else {
			throw new BusinessException("添加属性失败");
		}
	}

	@Override
	public Long updateAttributeNameWithTx(AttributeNamePO po, List<AttValuePO> lists, String begin, String finish) {

		if (po.getMode() == 1 || po.getMode() == 3) {
			// 根据属性值id是否为空添加属性值信息
			for (AttValuePO attValue : lists) {
				if (EmptyUtil.isEmpty(attValue.getAttValue())) {
					throw new BusinessException(BusinessExceptionConstant.ATT_VALUE_NO_NULL, "属性值名称不能为空！");
				}
				if(EmptyUtil.isBlank(attValue.getSpecificationCode())) {
					throw new BusinessException(BusinessExceptionConstant.SPECIFICATIONCODE_VALUE_NO_NULL, "属性值规格码不能为空！");
				}
				if (attValue.getId().equals(0L)) {
					AttributeValuePO attributeValueDTO = new AttributeValuePO();
					attributeValueDTO.setParentId(attValue.getParentId());
					attributeValueDTO.setAttributeNameId(po.getId());
					attributeValueDTO.setSortValue(attValue.getSortValue());
					attributeValueDTO.setValue(attValue.getAttValue());
					attributeValueDTO.setSpecificationCode(attValue.getSpecificationCode());
					attributeValueWriteDAO.insert(attributeValueDTO);
				} else {
					AttributeValuePO attributeValuePO = new AttributeValuePO();
					attributeValuePO.setId(attValue.getId());
					AttributeValuePO attributeValuePO2 = attributeValueReadDAO.findById(attributeValuePO);
					attributeValuePO2.setParentId(attValue.getParentId());
					attributeValuePO2.setAttributeNameId(po.getId());
					attributeValuePO2.setValue(attValue.getAttValue());
					attributeValuePO2.setSortValue(attValue.getSortValue());
					attributeValuePO2.setSpecificationCode(attValue.getSpecificationCode());
					attributeValueWriteDAO.update(attributeValuePO2);
				}
			}
		} else if (po.getMode() == 4) {
			AttributeNameDecimalPO attributeNameDecimalDTO = new AttributeNameDecimalPO();
			attributeNameDecimalDTO.setAttributeNameId(po.getId());
			if (!StringUtils.regularPositive(begin) || !StringUtils.regularPositive(finish)
					|| !StringUtils.isNotFigure(begin) || !StringUtils.isNotFigure(finish)) {
				throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入0-9999数字");
			} else {
				BigDecimal beginBigDecimal = new BigDecimal(begin);
				BigDecimal finishBigDecimal = new BigDecimal(finish);
				if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
					throw new BusinessException("属性范围终值不能小于起始值");
				}
				if (EmptyUtil.isNotEmpty(begin)) {
					attributeNameDecimalDTO.setBeginDecimal(beginBigDecimal);
				}
				if (EmptyUtil.isNotEmpty(finish)) {
					attributeNameDecimalDTO.setFinishDecimal(finishBigDecimal);
				}
			}
			// 根据属性id更新属性范围信息
			attributeNameDecimalWriteDAO.updateAttributeNameDecimalByAttNameIdWithTx(attributeNameDecimalDTO);
		} else if (po.getMode() == 2) {
			AttributeNameDecimalPO attributeNameDecimalDTO = new AttributeNameDecimalPO();
			attributeNameDecimalDTO.setAttributeNameId(po.getId());
			attributeNameDecimalDTO.setUnit(po.getUnit());
			if (!StringUtils.regularPositive(begin) || !StringUtils.regularPositive(finish)
					|| !StringUtils.isNotFigure(begin) || !StringUtils.isNotFigure(finish) || begin.length() > 4
					|| finish.length() > 4) {
				throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入0-9999数字");
			} else {
				BigDecimal beginBigDecimal = new BigDecimal(begin);
				BigDecimal finishBigDecimal = new BigDecimal(finish);
				if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
					throw new BusinessException("属性范围终值不能小于起始值");
				}
				if (EmptyUtil.isNotEmpty(begin)) {
					attributeNameDecimalDTO.setBeginDecimal(beginBigDecimal);
				}
				if (EmptyUtil.isNotEmpty(finish)) {
					attributeNameDecimalDTO.setFinishDecimal(finishBigDecimal);
				}
			}
			// 根据属性id更新属性范围信息
			attributeNameDecimalWriteDAO.updateAttributeNameDecimalByAttNameIdWithTx(attributeNameDecimalDTO);
		} else if (po.getMode() == 5) {
			AttributeNameDecimalPO attributeNameDecimalDTO = new AttributeNameDecimalPO();
			attributeNameDecimalDTO.setAttributeNameId(po.getId());
			attributeNameDecimalDTO.setUnit(po.getUnit());
			if (!StringUtils.regularPositive(begin) || !StringUtils.regularPositive(finish)
					|| !StringUtils.isNotFigure(begin) || !StringUtils.isNotFigure(finish) || begin.length() > 4
					|| finish.length() > 4) {
				throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入0-9999数字");
			} else {
				BigDecimal beginBigDecimal = new BigDecimal(begin);
				BigDecimal finishBigDecimal = new BigDecimal(finish);
				if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
					throw new BusinessException("属性范围终值不能小于起始值");
				}
				if (EmptyUtil.isNotEmpty(begin)) {
					attributeNameDecimalDTO.setBeginDecimal(beginBigDecimal);
				}
				if (EmptyUtil.isNotEmpty(finish)) {
					attributeNameDecimalDTO.setFinishDecimal(finishBigDecimal);
				}
			}
			attributeNameDecimalDTO.setImportHint(po.getImportHint());
			// 根据属性id更新属性范围信息
			attributeNameDecimalWriteDAO.updateAttributeNameDecimalByAttNameIdWithTx(attributeNameDecimalDTO);
		}else if(po.getMode() == 6){
			AttributeNameDecimalPO attributeNameDecimalDTO = new AttributeNameDecimalPO();
			attributeNameDecimalDTO.setAttributeNameId(po.getId());
			attributeNameDecimalDTO.setUnit(po.getUnit());
			if (EmptyUtil.isEmpty(begin) || EmptyUtil.isEmpty(finish) || !StringUtils.isNotFigure(begin)
					|| !StringUtils.isNotFigure(finish)
					|| new BigDecimal(begin).doubleValue() > BigDecimal.valueOf(9999.99).doubleValue()
					|| new BigDecimal(finish).doubleValue() > BigDecimal.valueOf(9999.99).doubleValue()
					|| BigDecimal.valueOf(-9999.99).doubleValue() > new BigDecimal(finish).doubleValue()
					|| BigDecimal.valueOf(-9999.99).doubleValue() > new BigDecimal(begin).doubleValue()) {
				throw new BusinessException(BusinessExceptionConstant.STRING_NO_FIGURE, "属性范围请输入-9999.99-9999.99数字");
			} else {
				BigDecimal beginBigDecimal = new BigDecimal(begin);
				BigDecimal finishBigDecimal = new BigDecimal(finish);
				if (beginBigDecimal.doubleValue() > finishBigDecimal.doubleValue()) {
					throw new BusinessException("属性范围终值不能小于起始值");
				}
				if (EmptyUtil.isNotEmpty(begin)) {
					attributeNameDecimalDTO.setBeginDecimal(beginBigDecimal);
				}
				if (EmptyUtil.isNotEmpty(finish)) {
					attributeNameDecimalDTO.setFinishDecimal(finishBigDecimal);
				}
			}
			// 根据属性id更新属性范围信息
			attributeNameDecimalWriteDAO.updateAttributeNameDecimalByAttNameIdWithTx(attributeNameDecimalDTO);
		}
		attributeNameWriteDAO.update(po);
		return po.getId();
	}

	@Override
	public Long deleteByIdWithTx(AttributeNamePO po) {
		attributeNameWriteDAO.delete(po);
		return po.getId();
	}

	@Override
	public int updateAttributeNameWithTx(AttributeNamePO po) {
		// TODO Auto-generated method stub
		return attributeNameWriteDAO.update(po);
	}

	@Override
	public Long saveAttributeNameWithTx(AttributeNamePO po) {
		attributeNameWriteDAO.insert(po);
		return po.getId();
	}
}
