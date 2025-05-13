package com.egeo.components.product.facade;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.read.MerchantProductFeedbackReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.write.MerchantProductFeedbackWriteService;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class MerchantProductFeedbackFacade {
	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource
	private MerchantProductFeedbackReadService merchantProductFeedbackReadService;
	
	@Resource
	private MerchantProductFeedbackWriteService merchantProductFeedbackWriteService;

	@Resource
	private StandardUnitReadService standardUnitReadService;

	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;

	@Autowired
	private UserExtendClient userExtendReadService;

	public MerchantProductFeedbackDTO findMerchantProductFeedbackById(MerchantProductFeedbackDTO dto){
		
		return merchantProductFeedbackReadService.findMerchantProductFeedbackById(dto);
	}

	public PageResult<MerchantProductFeedbackDTO> findMerchantProductFeedbackOfPage(MerchantProductFeedbackDTO dto,Pagination page){
		
		return merchantProductFeedbackReadService.findMerchantProductFeedbackOfPage(dto, page);
		
	}

	public List<MerchantProductFeedbackDTO> findMerchantProductFeedbackAll(MerchantProductFeedbackDTO dto){
		
		return merchantProductFeedbackReadService.findMerchantProductFeedbackAll(dto);
		
	}

	public Long insertMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto){

		return merchantProductFeedbackWriteService.insertMerchantProductFeedbackWithTx(dto);
	}

	public int updateMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto){
		
		return merchantProductFeedbackWriteService.updateMerchantProductFeedbackWithTx(dto);
	}

	public int deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto){
		
		return merchantProductFeedbackWriteService.deleteMerchantProductFeedbackWithTx(dto);
		
	}

	public MerchantProductFeedbackDTO findPuAndSuInfo(MerchantProductFeedbackDTO obj){
		CommodityProductUnitDTO puDto = new CommodityProductUnitDTO();
		StandardUnitDTO suDto = new StandardUnitDTO();
		puDto.setId(obj.getMerchantProductId());
		CommodityProductUnitDTO pu = commodityProductUnitReadService.findCommodityProductUnitById(puDto);
		if (EmptyUtil.isEmpty(pu)) {
			throw new BusinessException("pu不存在");
		}
		suDto.setId(pu.getStandardUnitId());
		StandardUnitDTO su =standardUnitReadService.findStandardUnitById(suDto);
		if (EmptyUtil.isEmpty(su)) {
			throw new BusinessException("su不存在");
		}
		obj.setMerchantProductSerialNumber(su.getMerchantProductSerialNumber());
		obj.setMerchantProductName(su.getName());
		obj.setAttributeName(pu.getName());
		obj.setSalePrice(pu.getSalePrice());
		return  obj;
	}

	public UserExtendDTO queryUserByUserId(Long userId){
		return userExtendReadService.userByUserId(userId);
	}
}
	