package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface StoreManage {

	public StoreDTO findStoreById(StoreDTO dto);	

	public PageResult<StoreDTO> findStoreOfPage(StoreDTO dto,Pagination page);

	public List<StoreDTO> findStoreAll(StoreDTO dto);

	Long insertStoreWithTx(StoreDTO dto);

	int updateStoreWithTx(StoreDTO dto);

	int deleteStoreWithTx(StoreDTO dto);

	public List<StoreDTO> findRootStoreAll(StoreDTO dto);

	public PageResult<StoreDTO> findRootStoreOfPage(StoreDTO dto, Pagination page);

	public List<StoreDTO> findStoreAllByTreeId(Long storeTreeId);

	PageResult<StoreDTO> findStoreOfPage(String flag, StoreDTO dto, Pagination page);

	public StoreDTO findStoreByNodeId(Long nodeId);
	
	List<Map<String, Object>> findCouponStore(StoreDTO dto);

    JsonResult<Map<String,String>> getStoreInfo(Long storeId, Long platformId);

	public List<StoreDTO> findStoreByPlatformIdAndStoreMenu(StoreDTO dto);
}
	