package com.egeo.components.order.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.common.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.condition.SoItemCondition;
import com.egeo.components.order.converter.SoItemConverter;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.manage.read.SoItemReadManage;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.service.read.SoItemReadService;


@Service("soItemReadService")
public class SoItemReadServiceImpl  implements SoItemReadService {
	@Autowired
	private SoItemReadManage soItemReadManage;

	@Override
	public List<SoItemDTO> querySoItemListByMerchantIds(List<Long> merchantIdList,Long platformId) {
		List<SoItemPO> soItems=soItemReadManage.querySoItemListByMerchantIds(merchantIdList,platformId);
		return SoItemConverter.toDTO(soItems);
	}

	@Override
	public List<SoItemDTO> querySoItemBySoId(Long soId) {
		 List<SoItemPO> soItems=soItemReadManage.querySoItemBySoId(soId);
		 return SoItemConverter.toDTO(soItems);
	}

	@Override
	public List<SoItemDTO> querySoItemListByPackId(Long packId, Long platformId) {
		List<SoItemPO> list = soItemReadManage.querySoItemListByPackId(packId, platformId);
		return SoItemConverter.toDTO(list);
	}

	@Override
	public List<SoItemDTO> findAll(SoItemDTO soItemDTO) {
		List<SoItemPO> list = soItemReadManage.findAll(SoItemConverter.toPO(soItemDTO));
		return SoItemConverter.toDTO(list);
	}

	@Override
	public List<SoItemDTO> soItemByPackageId(Long id) {
		List<SoItemPO> list = soItemReadManage.soItemByPackageId(id);
		return SoItemConverter.toDTO(list);
	}

	@Override
	public List<SoItemDTO> querySoItemListBySoId(Long id) {
		return SoItemConverter.toDTO(soItemReadManage.querySoItemListBySoId(id));
	}
	/**
	 * 查询是否该订单是否存在unit商品
	 * @param soItemDTO
	 * @return
	 */
	@Override
	public List<SoItemDTO> findAllBySoIdAndUnitExist(SoItemDTO soItemDTO) {
		List<SoItemDTO> list = new ArrayList<>();
		List<SoItemCondition> soItemConditionList = soItemReadManage.findAllBySoIdAndUnitExist(SoItemConverter.toPO(soItemDTO));
		for (SoItemCondition soItemCondition : soItemConditionList) {
			SoItemDTO itemDTO = SoItemConverter.toDTO(soItemCondition);
			list.add(itemDTO);
		}
		return list;
	}

	@Override
	public List<SoItemDTO> querySoItemsBySoChildId(Long id) {
		SoItemPO cond=new SoItemPO();
		cond.setSoChildId(id);
		return SoItemConverter.toDTO(soItemReadManage.findAll(cond));
	}

	@Override
	public Long findPUNum(Long id) {
		return soItemReadManage.finPUNum(id);
	}

	@Override
	public Long findPuIdBySoChildId(Long soChildId) {
		return soItemReadManage.findPuIdBySoChildId(soChildId);
	}

	@Override
	public Long findSoChildPUNum(Long id) {
		return soItemReadManage.findSoChildPUNum(id);
	}

	@Override
	public List<Long> findPuIdBySoId(Long id) {
		return soItemReadManage.findPuIdBySoId(id);
	}

	@Override
	public List<SoItemDTO> findSoItemByPuId(Long puId, Integer status) {

		return SoItemConverter.conditionToDTO(soItemReadManage.findSoItemByPuId(puId,status));
	}

	@Override
	public List<SoItemDTO> findSoItemByPuId(List<Long> puIds, Integer status) {
		return SoItemConverter.conditionToDTO(soItemReadManage.findSoItemByPuId(puIds,status));
	}

	@Override
	public List<String> findProductIdsSoChild(Long soChildId){
		return soItemReadManage.findProductIdsSoChild(soChildId);
	}

	@Override
	public List<SoItemDTO> findSoItemsSoChild(Long soChildId){
		List<SoItemPO> soItems = soItemReadManage.findSoItemsSoChild(soChildId);
		 return SoItemConverter.toDTO(soItems);
	}

	@Override
	public SoItemDTO getById(Long id){
		if(id ==null){
			throw new BusinessException("soItemId不能为空");
		}
		SoItemPO soItemPO =  soItemReadManage.getById(id);
		return SoItemConverter.toDTO(soItemPO);
	}

	@Override
	public List<SoItemDTO> getByIds(List<Long> ids){
		List<SoItemPO> soItemPOs = soItemReadManage.getByIds(ids);
		return SoItemConverter.toDTO(soItemPOs);
	}
}
