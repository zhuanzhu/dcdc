package com.egeo.components.product.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.FreightTemplateManage;
import com.egeo.components.product.facade.FreightTemplateFacade;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("freightTemplate")
public class FreightTemplateManageImpl implements FreightTemplateManage{

	
	@Resource(name="freightTemplateFacade")
	private FreightTemplateFacade freightTemplateFacade;
	/**
	 * 根据运费模版Id查询运费模版信息
	 * @param freightTemplateId
	 * @return
	 */
	@Override
	public Map<String, Object> findFreightTemplateById(Long freightTemplateId) {
		return freightTemplateFacade.findFreightTemplateById(freightTemplateId);
	}

	@Override
	public PageResult<Map<String, Object>> findFreightTemplateOfPage(FreightTemplateDTO dto, Pagination page) {
		return freightTemplateFacade.findFreightTemplateOfPage(dto, page);
	}

	@Override
	public List<FreightTemplateDTO> findFreightTemplateAll(FreightTemplateDTO dto) {
		return freightTemplateFacade.findFreightTemplateAll(dto);
	}
	/**
	 * 新增运费模版
	 * @param dto
	 * @param freightRegulationList
	 * @return
	 */
	@Override
	public Long insertFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList) {
		if(EmptyUtil.isEmpty(dto.getName())){
			throw new BusinessException("请输入模版名称");
		}
		//根据模版名称查询运费模版
		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		freightTemplateDTO.setName(dto.getName());
		List<FreightTemplateDTO> list = freightTemplateFacade.findFreightTemplateAll(freightTemplateDTO);
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("模版名称已存在，请重新输入");
		}
		//验证运费模版参数
		verificationFreightTemplate(dto,freightRegulationList);
		
		return freightTemplateFacade.insertFreightTemplateWithTx(dto,freightRegulationList);
	}
	/**
	 * 验证运费模版参数
	 * @param dto
	 * @param freightRegulationList
	 */
	private void verificationFreightTemplate(FreightTemplateDTO dto, List<FreightRegulationDTO> freightRegulationList) {
		if(dto.getName().length() > 30){
			throw new BusinessException("模版名称最多不超过30字");
		}
		if(dto.getIsExemption() == 0){
			if(EmptyUtil.isEmpty(freightRegulationList)){
				 throw new BusinessException("运费规则不能为空");
			}
			Long orderMoney = 0L;
			Long freightMoney = null;
			//此出金额类型需前端处理、只能为数字
			for (FreightRegulationDTO freightRegulationDTO : freightRegulationList) {
				if(EmptyUtil.isEmpty(freightRegulationDTO.getOrderMoney()) || EmptyUtil.isEmpty(freightRegulationDTO.getFreightMoney())){
					throw new BusinessException("请完善运费规则信息");
				}
				if(freightRegulationDTO.getOrderMoney().longValue() < 0L || freightRegulationDTO.getFreightMoney().longValue() < 0L){
					throw new BusinessException("金额不能为负数");
				}
				if(freightRegulationDTO.getOrderMoney().longValue() == 0L){
					throw new BusinessException("订单金额不能为0");
				}
				if(orderMoney.longValue() >= freightRegulationDTO.getOrderMoney()){
					throw new BusinessException("输入的订单金额需呈阶梯型增长");
				}
				orderMoney = freightRegulationDTO.getOrderMoney();
				if(freightMoney == null){
					freightMoney = freightRegulationDTO.getFreightMoney();
				}else{
					if(freightMoney.longValue() <= freightRegulationDTO.getFreightMoney().longValue()){
						throw new BusinessException("输入的运费金额需呈阶梯性下降");
					}
				}
				
			}
		}
		if(EmptyUtil.isEmpty(dto.getShipmentsExplain())){
			throw new BusinessException("发货说明不能为空");
		}
		if(dto.getShipmentsExplain().length() > 30){
			throw new BusinessException("发货说明最多不超过30个字");
		}
		
	}

	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	@Override
	public int updateFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList) {
		if(EmptyUtil.isEmpty(dto.getName())){
			throw new BusinessException("请输入模版名称");
		}
		//根据模版名称查询运费模版
		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		freightTemplateDTO.setName(dto.getName());
		List<FreightTemplateDTO> list = freightTemplateFacade.findFreightTemplateAll(freightTemplateDTO);
		if(EmptyUtil.isNotEmpty(list)){
			if(!list.get(0).getId().equals(dto.getId())){
				throw new BusinessException("模版名称已存在，请重新输入");
			}
			
		}
		//验证运费模版参数
		verificationFreightTemplate(dto,freightRegulationList);
		return freightTemplateFacade.updateFreightTemplateWithTx(dto,freightRegulationList);
	}

	@Override
	public int deleteFreightTemplateWithTx(FreightTemplateDTO dto) {
		return freightTemplateFacade.deleteFreightTemplateWithTx(dto);
	}
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	@Override
	public int startFreightTemplateWithTx(Long freightTemplateId) {
		// TODO Auto-generated method stub
		return freightTemplateFacade.startFreightTemplateWithTx(freightTemplateId);
	}
	/**
	 * 根据商家Id查询运费模版信息
	 * @param freightTemplateId
	 * @return
	 */
	@Override
	public Map<String, Object> freightTemplateByMerchantId(Long merchantId,Long storeId,Long platformId) {
		// TODO Auto-generated method stub
		return freightTemplateFacade.freightTemplateByMerchantId(merchantId,storeId,platformId);
	}


}
	