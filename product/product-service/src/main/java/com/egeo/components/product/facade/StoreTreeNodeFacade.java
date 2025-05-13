package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.read.StoreProductUnitReadService;
import com.egeo.components.product.service.read.StoreTreeNodeReadService;
import com.egeo.components.product.service.write.StoreProductUnitWriteService;
import com.egeo.components.product.service.write.StoreTreeNodeWriteService;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreTreeNodeFacade {
	
	@Resource
	private StoreTreeNodeReadService storeTreeNodeReadService;
	
	@Resource
	private StoreTreeNodeWriteService storeTreeNodeWriteService;
	
	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;
	
	@Resource
	private StoreProductUnitWriteService storeProductUnitWriteService;
	
	@Resource
	private StoreProductUnitReadService storeProductUnitReadService;
	
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockWriteService;
	
	
	public StoreTreeNodeDTO findStoreTreeNodeById(StoreTreeNodeDTO dto){
		
		return storeTreeNodeReadService.findStoreTreeNodeById(dto);
	}

	public PageResult<StoreTreeNodeDTO> findStoreTreeNodeOfPage(StoreTreeNodeDTO dto,Pagination page){
		
		return storeTreeNodeReadService.findStoreTreeNodeOfPage(dto, page);
		
	}

	public List<StoreTreeNodeDTO> findStoreTreeNodeAll(StoreTreeNodeDTO dto){
		
		return storeTreeNodeReadService.findStoreTreeNodeAll(dto);
		
	}

	public Long insertStoreTreeNodeWithTx(StoreTreeNodeDTO storeTreeNodedto,StoreDTO storedto){
		Long storeId = storeTreeNodeWriteService.insertStoreTreeNodeWithTx(storeTreeNodedto,storedto);
		// 是否为总店，不为总店批量生成门店pu
		if(storeTreeNodedto.getParentId() != 0){
			createStoreProductUnit(storeId,storeTreeNodedto.getPlatformId());
		}
		return storeId;
	}
	/**
	 * 批量生成门店pu和库存
	 * @param storeId
	 * @param platformId
	 */
	private void createStoreProductUnit(Long storeId, Long platformId) {
		// 根据平台id查询pu信息
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setPlatformId(platformId);
		List<CommodityProductUnitDTO> commodityProductUnitList = commodityProductUnitReadService.findCommodityProductUnitAll(commodityProductUnitDTO);
		List<StoreProductUnitDTO> list = new ArrayList<>(commodityProductUnitList.size());
		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
			storeProductUnitDTO.setCommodityProductUnitId(commodityProductUnitDTO2.getId());
			storeProductUnitDTO.setStandardUnitId(commodityProductUnitDTO2.getStandardUnitId());
			storeProductUnitDTO.setStoreId(storeId);
			storeProductUnitDTO.setPlatformId(commodityProductUnitDTO2.getPlatformId());
			storeProductUnitDTO.setStatus(commodityProductUnitDTO2.getStatus());
			list.add(storeProductUnitDTO);
		}
		// 批量生成门店pu
		storeProductUnitWriteService.insertAllWithTx(list);
		
		// 创建门店库存信息集合
		List<StorePuWarehouseStockDTO> storePuWarehouseStockList = createStorePuWarehouseStock(storeId,platformId);
		
		// 批量保存门店pu库存信息
		storePuWarehouseStockWriteService.insertAllWithTx(storePuWarehouseStockList);
	}
	
	/**
	 * 创建门店库存信息集合
	 * @param standardUnitId
	 * @param platformId
	 * @return
	 */
	private List<StorePuWarehouseStockDTO> createStorePuWarehouseStock(Long storeId, Long platformId) {
		// 商家默认为1
		Long merchantId = 1L;
		// 根据su商品id查询门店pu信息
		StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
		storeProductUnitDTO.setStoreId(storeId);
		storeProductUnitDTO.setPlatformId(platformId);
		List<StoreProductUnitDTO> storeProductUnitList = storeProductUnitReadService.findStoreProductUnitAll(storeProductUnitDTO);
		List<StorePuWarehouseStockDTO> list = new ArrayList<>(storeProductUnitList.size());
		for (StoreProductUnitDTO storeProductUnitDTO2 : storeProductUnitList) {
			StorePuWarehouseStockDTO storePuWarehouseStockDTO = new StorePuWarehouseStockDTO();
			storePuWarehouseStockDTO.setMerchantId(merchantId);
			storePuWarehouseStockDTO.setRealFrozenStockNum(0L);
			storePuWarehouseStockDTO.setRealStockNum(0L);
			storePuWarehouseStockDTO.setStandardUnitId(storeProductUnitDTO2.getStandardUnitId());
			storePuWarehouseStockDTO.setStoreProductUnitId(storeProductUnitDTO2.getId());
			storePuWarehouseStockDTO.setStoreId(storeProductUnitDTO2.getStoreId());
			storePuWarehouseStockDTO.setPlatformId(platformId);
			list.add(storePuWarehouseStockDTO);
		}
		return list;
	}

	public int updateStoreTreeNodeWithTx(StoreTreeNodeDTO dto){
		
		return storeTreeNodeWriteService.updateStoreTreeNodeWithTx(dto);
	}

	public int deleteStoreTreeNodeWithTx(StoreTreeNodeDTO dto){
		
		return storeTreeNodeWriteService.deleteStoreTreeNodeWithTx(dto);
		
	}
	/**
	 * 根据门店id查询是否是总店
	 * @param storeId
	 * @return
	 */
	public boolean findHeadStoreByStoreId(Long storeId) {
		return storeTreeNodeReadService.findHeadStoreByStoreId(storeId);
	}

}
	