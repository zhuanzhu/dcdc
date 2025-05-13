package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StoreReadService {

	public StoreDTO findStoreById(StoreDTO dto);

	public PageResult<StoreDTO> findStoreOfPage(StoreDTO dto,Pagination page);

	public List<StoreDTO> findStoreAll(StoreDTO dto);

	public List<StoreDTO> findRootStoreAll(StoreDTO dto);

	public PageResult<StoreDTO> findRootStoreOfPage(StoreDTO dto, Pagination page);

	public List<StoreDTO> findStoreAllByTreeId(Long storeTreeId);
	/**
	 * 根据门店id查询总店信息
	 * @param id
	 * @return
	 */
	public StoreDTO findHeadStoreByStoreId(Long storeId);

	/**
	 * 根据门店节点id查询总店信息
	 * @param id
	 * @return
	 */
	public StoreDTO findStoreByNodeId(Long nodeId);
	
	/**
	 * 查询优惠券关联门店，拼装门店名称
	 * @param storeDTO
	 * @return
	 */
	public List<StoreDTO> findCouponStore(StoreDTO storeDTO);

    List<Long> findStoreByName(String storeName);

	public List<StoreDTO> findStoreByPlatformIdAndStoreMenu(StoreDTO dto);
}
	