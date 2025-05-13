package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitStoreReadService {

	public StandardUnitStoreDTO findStandardUnitStoreById(StandardUnitStoreDTO dto);

	public PageResult<StandardUnitStoreDTO> findStandardUnitStoreOfPage(StandardUnitStoreDTO dto,Pagination page);

	public List<StandardUnitStoreDTO> findStandardUnitStoreAll(StandardUnitStoreDTO dto);
	/**
	 * 根据门店id查询在售商品数量(上架商品)
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	public Integer standardUnitSumByStoreId(Long storeId, Long platformId);
	/**
	 * 根据门店id查询puid集合
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	public List<Long> findByPuIdsByStoreId(Long storeId, Long platformId);
	/**
	 * 根据商品id查询所有门店（包含id和名称）
	 * @param dto
	 * @return
	 */
	public List<StandardUnitStoreDTO> standardUnitStoreByStandardUnitId(StandardUnitStoreDTO dto);
	
	/**
	 * 根据suID和门店ID查询
	 * @param suIdList
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	List<StandardUnitStoreDTO> findByStoreAndSu(List<Long> suIdList, Long storeId, Long platformId);

    int findStandardUnitStoreCount(Long suId, Long storeId);
}
	