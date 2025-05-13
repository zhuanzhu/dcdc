package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.SkuPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SkuReadService;
import com.egeo.components.product.manage.read.SkuReadManage;
import com.egeo.components.product.condition.SkuCondition;
import com.egeo.components.product.converter.SkuConverter;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.po.SkuPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("skuReadService")
public class SkuReadServiceImpl  implements SkuReadService {
	@Autowired
	private SkuReadManage skuReadManage;

	@Override
	public SkuDTO findSkuById(SkuDTO dto) {
		SkuPO po = SkuConverter.toPO(dto);
		SkuPO list = skuReadManage.findSkuById(po);		
		return SkuConverter.toDTO(list);
	}

	@Override
	public PageResult<SkuDTO> findSkuOfPage(SkuDTO dto, Pagination page) {
		SkuPO po = SkuConverter.toPO(dto);
		List<SkuDTO> list = new ArrayList<>();
        PageResult<SkuCondition> pageResult = skuReadManage.findSkuOfPage(po, page);
        List<SkuCondition> skuConditionList = pageResult.getList();
        for (SkuCondition skuCondition : skuConditionList) {
        	SkuDTO skuDTO = SkuConverter.toDTO(skuCondition);
        	skuDTO.setStandardProductUnitName(skuCondition.getName());
        	list.add(skuDTO);
		}
        
        PageResult<SkuDTO> result = new PageResult<SkuDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SkuDTO> findSkuAll(SkuDTO dto) {
		List<SkuDTO> skuList = new ArrayList<>();
		SkuPO po = SkuConverter.toPO(dto);
		List<SkuCondition> list = skuReadManage.findSkuAll(po);		
		for (SkuCondition skuCondition : list) {
			SkuDTO skuDTO = SkuConverter.toDTO(skuCondition);
			skuDTO.setSkuProductName(skuCondition.getName());
			skuList.add(skuDTO);
		}
		return skuList;
	}
	/**
	 * 分页查询所有电子卡券sku
	 * @return
	 */
	@Override
	public List<SkuDTO> findSkuECardOfPage(SkuDTO dto) {
		SkuPO po = SkuConverter.toPO(dto);
        List<SkuCondition> result = skuReadManage.findSkuECardOfPage(po);

		List<SkuDTO> list = new ArrayList<>();
		for (SkuCondition skuCondition : result) {
			SkuDTO skuDTO = SkuConverter.toDTO(skuCondition);
			skuDTO.setStandardProductUnitName(skuCondition.getName());
			list.add(skuDTO);
		}

        return list;
	}
	/**
	 * 根据sku编号查询sku电子卡券信息
	 * @param skuSerialNumber
	 * @return
	 */
	@Override
	public SkuDTO findSkuECardBySkuSerialNumber(String skuSerialNumber) {
		SkuPO skuPO = skuReadManage.findSkuECardBySkuSerialNumber(skuSerialNumber);
		if(EmptyUtil.isNotEmpty(skuPO)){
			return SkuConverter.toDTO(skuPO);
		}
		return null;
	}

	@Override
	public Map<String, String> queryStandardBySkuId(Long skuId) {
		return skuReadManage.queryStandardBySkuId(skuId);
	}

	@Override
	public List<SkuDTO> findSkuLikeName(String linkedSkuName,Long platformId) {
		List<SkuPO> skuPOList=skuReadManage.findSkuLikeName(linkedSkuName,platformId);
		if(EmptyUtil.isEmpty(skuPOList)){
			return null;

		}
		return SkuConverter.toDTO(skuPOList);
	}

	@Override
	public List<Long> getMembershipSku(Long platformId) {
		List<Long> membershipSku = skuReadManage.getMembershipSku(platformId);
		if(membershipSku==null){
			return null;
		}
		return membershipSku;
	}

	@Override
	public SkuDTO findSkuByPuId(Long puId) {
		return SkuConverter.toDTO(skuReadManage.findSkuByPuId(puId));
	}


	/**
	 * 根据预警属性级别id查询sku集合的id和预警天数
	 */
	@Override
	public Map<Long, String> findSkuIdAndPreDaysByPreAttNameId(Long precautiousAttNameId) {

		return skuReadManage.findSkuIdAndPreDaysByPreAttNameId(precautiousAttNameId);
	}

	@Override
	public Long findLastId() {
		return skuReadManage.findLastId();
	}

	@Override
	public List<SkuPriceDTO> findSkuListConByIdList(List<Long> idList) {
		List<SkuCondition> res = skuReadManage.findSkuListConByIdList(idList);
		List<SkuPriceDTO> list = new ArrayList<>();
		for(SkuCondition co:res){
			SkuPriceDTO skuPriceDTO = new SkuPriceDTO();
			skuPriceDTO.setCommodityProductUnitId(co.getCommodityProductUnitId());
			skuPriceDTO.setExternalSkuId(Long.valueOf(co.getExternalSkuId()));
			skuPriceDTO.setMerchantProductId(co.getMerchantProductId());
			skuPriceDTO.setProductUnitId(co.getProductUnitId());
			skuPriceDTO.setStandardUnitId(co.getStandardUnitId());
			list.add(skuPriceDTO);

		}

		return list;
	}


	@Override
	public List<SkuDTO> findSkuBySkuSerialNos(List<String> skuSerialNos) {
		return SkuConverter.toDTO(skuReadManage.findSkuBySkuSerialNos(skuSerialNos));
	}

	@Override
	public List<SkuDTO> findSkuBySkuIds(List<Long> skuIds) {
		return SkuConverter.toDTO(skuReadManage.findSkuBySkuIds(skuIds));
	}
}
	