package com.egeo.components.stock.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.components.stock.service.read.StoreStockChangeApplyReadService;
import com.egeo.components.stock.service.write.StoreStockChangeApplyWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreStockChangeApplyFacade {
	
	@Resource
	private StoreStockChangeApplyReadService storeStockChangeApplyReadService;
	
	@Resource
	private StoreStockChangeApplyWriteService storeStockChangeApplyWriteService;
	
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	
	
	public StoreStockChangeApplyDTO findStoreStockChangeApplyById(StoreStockChangeApplyDTO dto){
		
		return storeStockChangeApplyReadService.findStoreStockChangeApplyById(dto);
	}

	public PageResult<StoreStockChangeApplyDTO> findStoreStockChangeApplyOfPage(StoreStockChangeApplyDTO dto,Pagination page){
		
		return storeStockChangeApplyReadService.findStoreStockChangeApplyOfPage(dto, page);
		
	}

	public List<StoreStockChangeApplyDTO> findStoreStockChangeApplyAll(StoreStockChangeApplyDTO dto){
		
		return storeStockChangeApplyReadService.findStoreStockChangeApplyAll(dto);
		
	}

	public Long insertStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto,List<String> pictures){
		
		return storeStockChangeApplyWriteService.insertStoreStockChangeApplyWithTx(dto,pictures);
	}

	public int updateStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto){
		
		return storeStockChangeApplyWriteService.updateStoreStockChangeApplyWithTx(dto);
	}

	public int deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyDTO dto){
		
		return storeStockChangeApplyWriteService.deleteStoreStockChangeApplyWithTx(dto);
		
	}

	public Map<String, Object> findStoreStockChangeApplyOfPageAPP(StoreStockChangeApplyDTO dto, Pagination page) {
		Map<String, Object> data = new HashMap<>();
		PageResult<StoreStockChangeApplyDTO> rt = storeStockChangeApplyReadService.findStoreStockChangeApplyOfPage(dto, page);
		List<StoreStockChangeApplyDTO> storeStockChangeApplyList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>(storeStockChangeApplyList.size());
		for (StoreStockChangeApplyDTO storeStockChangeApplyDTO : storeStockChangeApplyList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", storeStockChangeApplyDTO.getId());
			map.put("commodityProductUnitName", storeStockChangeApplyDTO.getCommodityProductUnitName());
			map.put("isConsent", storeStockChangeApplyDTO.getIsConsent());
			map.put("stockChange", storeStockChangeApplyDTO.getStockChange());
			map.put("afterTime", storeStockChangeApplyDTO.getAfterTime());
			// 根据门店puid查询pu图片信息
			String picture = commodityProductUnitReadService.findPictureByStorePUId(
					storeStockChangeApplyDTO.getStoreProductUnitId());
			map.put("picture", picture);
			map.put("productUnitSerialNumber", storeStockChangeApplyDTO.getProductUnitSerialNumber());
			list.add(map);
		}
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
        data.put("result", result);
		return data;
	}

}
	