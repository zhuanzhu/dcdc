package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitStoreManage;
import com.egeo.components.product.facade.StandardUnitFacade;
import com.egeo.components.product.facade.StandardUnitStoreFacade;
import com.egeo.components.product.facade.StoreMenuNodeStandardUnitFacade;
import com.egeo.components.product.facade.StoreTreeNodeFacade;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("standardUnitStore")
public class StandardUnitStoreManageImpl implements StandardUnitStoreManage{

	
	@Resource(name="standardUnitStoreFacade")
	private StandardUnitStoreFacade standardUnitStoreFacade;
	
	@Resource(name="storeMenuNodeStandardUnitFacade")
	private StoreMenuNodeStandardUnitFacade storeMenuNodeStandardUnitFacade;
	
	@Resource(name="storeTreeNodeFacade")
	private StoreTreeNodeFacade storeTreeNodeFacade;
	
	@Resource(name = "standardUnitFacade")
    private StandardUnitFacade standardUnitFacade;

	@Override
	public StandardUnitStoreDTO findStandardUnitStoreById(StandardUnitStoreDTO dto) {
		return standardUnitStoreFacade.findStandardUnitStoreById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findStandardUnitStoreOfPage(StandardUnitStoreDTO dto, Pagination page) {
		// 根据门店id查询是否是总店
		if(storeTreeNodeFacade.findHeadStoreByStoreId(dto.getStoreId())){
			// 根据总店id查询su商品数据
			return dataResultByHeadStoreId(dto, page);
		}else{
			// 根据门店id查询su商品数据
			return dataResultByStoreId(dto, page);
		}
	}
	/**
	 * 根据总店id查询su商品数据
	 * @param dto
	 * @param page
	 * @return
	 */
	private PageResult<Map<String, Object>> dataResultByHeadStoreId(
			StandardUnitStoreDTO dto, Pagination page) {
		// 创建商品查询数据
		StandardUnitDTO standardUnitDTO = createStandardUnitDTO(dto);
		
		PageResult<StandardUnitDTO> rt = standardUnitFacade.findByHeadStoreId(standardUnitDTO,page);
		List<StandardUnitDTO> standardUnitList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>(standardUnitList.size());
		for (StandardUnitDTO standardUnit : standardUnitList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", standardUnit.getId());
			map.put("standardUnitSerialNumber", standardUnit.getMerchantProductSerialNumber());
			map.put("standardUnitName", standardUnit.getName());
			map.put("salePrice", standardUnit.getSalePrice());
			map.put("promotionPrice", standardUnit.getPromotionPrice());
			map.put("marketPrice", standardUnit.getMarketPrice());
			map.put("status", standardUnit.getStatus());
			map.put("isVisible", standardUnit.getIsVisible());
			list.add(map);
		}
		
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return result;
	}
	/**
	 * 创建商品查询数据
	 * @param dto
	 * @return
	 */
	private StandardUnitDTO createStandardUnitDTO(StandardUnitStoreDTO dto) {
		StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
		standardUnitDTO.setStoreId(dto.getStoreId());
		standardUnitDTO.setPlatformId(dto.getPlatformId());
		standardUnitDTO.setName(dto.getStandardUnitName());
		standardUnitDTO.setMerchantProductSerialNumber(dto.getStandardUnitSerialNumber());
		standardUnitDTO.setSalePriceStart(dto.getSalePriceStart());
		standardUnitDTO.setSalePriceStop(dto.getSalePriceStop());
		standardUnitDTO.setPromotionPriceStart(dto.getPromotionPriceStart());
		standardUnitDTO.setPromotionPriceStop(dto.getPromotionPriceStop());
		standardUnitDTO.setStatus(dto.getStatus());
		standardUnitDTO.setIsVisible(dto.getIsVisible());
		return standardUnitDTO;
	}

	/**
	 * 根据门店id查询su商品数据
	 * @param dto
	 * @param page
	 * @return
	 */
	private PageResult<Map<String, Object>> dataResultByStoreId(StandardUnitStoreDTO dto, Pagination page) {
		PageResult<StandardUnitStoreDTO> rt = standardUnitStoreFacade.findStandardUnitStoreOfPage(dto, page);
		List<StandardUnitStoreDTO> standardUnitStoreDTOList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>(standardUnitStoreDTOList.size());
		for (StandardUnitStoreDTO standardUnitStoreDTO : standardUnitStoreDTOList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", standardUnitStoreDTO.getStandardUnitId());
			map.put("standardUnitSerialNumber", standardUnitStoreDTO.getStandardUnitSerialNumber());
			map.put("standardUnitName", standardUnitStoreDTO.getStandardUnitName());
			map.put("salePrice", standardUnitStoreDTO.getSalePrice());
			map.put("promotionPrice", standardUnitStoreDTO.getPromotionPrice());
			map.put("marketPrice", standardUnitStoreDTO.getMarketPrice());
			map.put("status", standardUnitStoreDTO.getStatus());
			map.put("isVisible", standardUnitStoreDTO.getIsVisible());
			list.add(map);
		}
		
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return result;
	}

	@Override
	public Map<String, Object> findStandardUnitStoreAll(StandardUnitStoreDTO dto) {
		Map<String, Object> data = new HashMap<>();
		List<StandardUnitStoreDTO> standardUnitStoreList = standardUnitStoreFacade.findStandardUnitStoreAll(dto);
		List<Map<String, Object>> standardUnitList = new ArrayList<>(standardUnitStoreList.size());
		for (StandardUnitStoreDTO standardUnitStoreDTO : standardUnitStoreList) {
			Map<String, Object> map = new HashMap<>();
			map.put("standardUnitId", standardUnitStoreDTO.getStandardUnitId());
			map.put("standardUnitName", standardUnitStoreDTO.getStandardUnitName());
			standardUnitList.add(map);
		}
		data.put("standardUnitList", standardUnitList);
		
		// 根据门店菜单id查询su商品信息
		List<Long> standardUnitIds = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(dto.getStoreMenuNodeId())){
			StoreMenuNodeStandardUnitDTO storeMenuNodeStandardUnitDTO = new StoreMenuNodeStandardUnitDTO();
			storeMenuNodeStandardUnitDTO.setStoreMenuNodeId(dto.getStoreMenuNodeId());
			storeMenuNodeStandardUnitDTO.setPlatformId(dto.getPlatformId());
			List<StoreMenuNodeStandardUnitDTO> storeMenuNodeStandardUnitList = 
					storeMenuNodeStandardUnitFacade.findStoreMenuNodeStandardUnitAll(storeMenuNodeStandardUnitDTO);
			for (StoreMenuNodeStandardUnitDTO storeMenuNodeStandardUnitDTO2 : storeMenuNodeStandardUnitList) {
				standardUnitIds.add(storeMenuNodeStandardUnitDTO2.getStandardUnitId());
			}
		}
		data.put("standardUnitIds", standardUnitIds);
		return data;
	}

	@Override
	public Long insertStandardUnitStoreWithTx(StandardUnitStoreDTO dto) {
		return standardUnitStoreFacade.insertStandardUnitStoreWithTx(dto);
	}

	@Override
	public int updateStandardUnitStoreWithTx(StandardUnitStoreDTO dto) {
		return standardUnitStoreFacade.updateStandardUnitStoreWithTx(dto);
	}

	@Override
	public int deleteStandardUnitStoreWithTx(StandardUnitStoreDTO dto) {
		return standardUnitStoreFacade.deleteStandardUnitStoreWithTx(dto);
	}

	@Override
	public Map<String, Object> standardUnitStoreByStandardUnitId(StandardUnitStoreDTO dto) {
		return standardUnitStoreFacade.standardUnitStoreByStandardUnitId(dto);
	}


}
	