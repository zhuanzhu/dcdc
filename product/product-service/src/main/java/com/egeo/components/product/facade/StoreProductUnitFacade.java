package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.service.read.StoreProductUnitReadService;
import com.egeo.components.product.service.write.StoreProductUnitWriteService;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class StoreProductUnitFacade {
	
	@Resource
	private StoreProductUnitReadService storeProductUnitReadService;
	
	@Resource
	private StoreProductUnitWriteService storeProductUnitWriteService;
	
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockReadService;
	
	
	public StoreProductUnitDTO findStoreProductUnitById(StoreProductUnitDTO dto){
		
		return storeProductUnitReadService.findStoreProductUnitById(dto);
	}

	public PageResult<Map<String, Object>> findStoreProductUnitOfPage(StoreProductUnitDTO dto,Pagination page){
		PageResult<StoreProductUnitDTO> rt = storeProductUnitReadService.findStoreProductUnitOfPage(dto, page);
		List<StoreProductUnitDTO> storeProductUnitList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>(storeProductUnitList.size());
		for (StoreProductUnitDTO storeProductUnitDTO : storeProductUnitList) {
			Map<String, Object> map = new HashMap<>();
			// 根据门店puid查询门店pu库存信息
			StorePuWarehouseStockDTO storePuWarehouseStockDTO = storePuWarehouseStockReadService.findByStorePuId(storeProductUnitDTO.getId());
			if(EmptyUtil.isEmpty(storePuWarehouseStockDTO)){
				throw new BusinessException("门店pu编号："+ storeProductUnitDTO.getId() + "库存异常");
			}
			map.put("id", storeProductUnitDTO.getId());
			map.put("commodityProductUnitId",storeProductUnitDTO.getCommodityProductUnitId());
			map.put("commodityProductUnitName", storeProductUnitDTO.getCommodityProductUnitName());
			map.put("productUnitSerialNumber", storeProductUnitDTO.getProductUnitSerialNumber());
			map.put("salePrice", storeProductUnitDTO.getSalePrice());
			map.put("storeId", storeProductUnitDTO.getStoreId());
			map.put("storeName", storeProductUnitDTO.getStoreName());
			map.put("realStockNum", storePuWarehouseStockDTO.getRealStockNum());
			map.put("realFrozenStockNum", storePuWarehouseStockDTO.getRealFrozenStockNum());
			map.put("puPicUrl", storeProductUnitDTO.getPuPicUrl());
			map.put("saleWay", storeProductUnitDTO.getSaleWay());
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return result;
		
	}

	public List<StoreProductUnitDTO> findStoreProductUnitAll(StoreProductUnitDTO dto){
		
		return storeProductUnitReadService.findStoreProductUnitAll(dto);
		
	}

	public Long insertStoreProductUnitWithTx(StoreProductUnitDTO dto){
		
		return storeProductUnitWriteService.insertStoreProductUnitWithTx(dto);
	}

	public int updateStoreProductUnitWithTx(StoreProductUnitDTO dto){
		
		return storeProductUnitWriteService.updateStoreProductUnitWithTx(dto);
	}

	public int deleteStoreProductUnitWithTx(StoreProductUnitDTO dto){
		
		return storeProductUnitWriteService.deleteStoreProductUnitWithTx(dto);
		
	}

}
	