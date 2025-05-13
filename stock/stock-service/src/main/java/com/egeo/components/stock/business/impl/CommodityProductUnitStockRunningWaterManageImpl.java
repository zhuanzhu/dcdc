package com.egeo.components.stock.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.CommodityProductUnitStockRunningWaterManage;
import com.egeo.components.stock.facade.CommodityProductUnitStockRunningWaterFacade;
import com.egeo.components.stock.facade.ContactGroupStockFacade;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("commodityProductUnitStockRunningWater")
public class CommodityProductUnitStockRunningWaterManageImpl implements CommodityProductUnitStockRunningWaterManage{

	
	@Resource(name="commodityProductUnitStockRunningWaterFacade")
	private CommodityProductUnitStockRunningWaterFacade commodityProductUnitStockRunningWaterFacade;
	
	@Resource(name = "contactGroupStockFacade")
	private ContactGroupStockFacade contactGroupStockFacade;

	@Override
	public CommodityProductUnitStockRunningWaterDTO findCommodityProductUnitStockRunningWaterById(CommodityProductUnitStockRunningWaterDTO dto) {
		return commodityProductUnitStockRunningWaterFacade.findCommodityProductUnitStockRunningWaterById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterDTO dto, Pagination page) {
		List<Map<String, Object>> list = new ArrayList<>();
		PageResult<CommodityProductUnitStockRunningWaterDTO> rt = commodityProductUnitStockRunningWaterFacade.findCommodityProductUnitStockRunningWaterOfPage(dto, page);
		List<CommodityProductUnitStockRunningWaterDTO> commodityProductUnitStockRunningWaterList = rt.getList();
		for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : commodityProductUnitStockRunningWaterList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", commodityProductUnitStockRunningWaterDTO.getId());
			map.put("commodityProductUnitId", commodityProductUnitStockRunningWaterDTO.getCommodityProductUnitId());
			map.put("productUnitSerialNumber", commodityProductUnitStockRunningWaterDTO.getProductUnitSerialNumber());
			map.put("commodityProductUnitName", commodityProductUnitStockRunningWaterDTO.getCommodityProductUnitName());
			map.put("preoperativeStockNum", commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum());
			map.put("operationBackStockNum", commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum());
			map.put("stockChange", commodityProductUnitStockRunningWaterDTO.getStockChange());
			map.put("createUserid", commodityProductUnitStockRunningWaterDTO.getCreateUserid());
			map.put("createUsername", commodityProductUnitStockRunningWaterDTO.getCreateUsername());
			map.put("createTime", commodityProductUnitStockRunningWaterDTO.getCreateTime());
			map.put("preoperativeRealStockNum", commodityProductUnitStockRunningWaterDTO.getPreoperativeRealStockNum());
			map.put("operationBackRealStockNum", commodityProductUnitStockRunningWaterDTO.getOperationBackRealStockNum());
			map.put("orderCode", commodityProductUnitStockRunningWaterDTO.getOrderCode());
			map.put("type", commodityProductUnitStockRunningWaterDTO.getType());
			map.put("contactGroupStockName", commodityProductUnitStockRunningWaterDTO.getContactGroupStockName());
			contactGroupStockFacade.findContactGroupStockByPuId(commodityProductUnitStockRunningWaterDTO.getCommodityProductUnitId());
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
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterDTO dto) {
		return commodityProductUnitStockRunningWaterFacade.findCommodityProductUnitStockRunningWaterAll(dto);
	}

	@Override
	public Long insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto) {
		return commodityProductUnitStockRunningWaterFacade.insertCommodityProductUnitStockRunningWaterWithTx(dto);
	}

	@Override
	public int updateCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto) {
		return commodityProductUnitStockRunningWaterFacade.updateCommodityProductUnitStockRunningWaterWithTx(dto);
	}

	@Override
	public int deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto) {
		return commodityProductUnitStockRunningWaterFacade.deleteCommodityProductUnitStockRunningWaterWithTx(dto);
	}


}
	