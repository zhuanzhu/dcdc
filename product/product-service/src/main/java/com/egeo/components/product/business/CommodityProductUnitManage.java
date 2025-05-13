package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitManage {

	public CommodityProductUnitDTO findCommodityProductUnitById(CommodityProductUnitDTO dto);	

	public PageResult<CommodityProductUnitDTO> findCommodityProductUnitOfPage(CommodityProductUnitDTO dto,Pagination page);

	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(CommodityProductUnitDTO dto);

	Long insertCommodityProductUnitWithTx(CommodityProductUnitDTO dto);

	int updateCommodityProductUnitWithTx(CommodityProductUnitDTO dto);

	int deleteCommodityProductUnitWithTx(CommodityProductUnitDTO dto);
	/**
	 * 分页查询pu库存信息
	 * @param vo
	 * @param req
	 * @param realStockNumMin
     *@param realStockNumMax
     * @param page  @return
	 */
	public PageResult<Map<String, Object>> findCommodityProductUnitStockOfPage(CommodityProductUnitDTO dto,
                                                                               Pagination page);
	/**
	 * 根据suid查询su所有pu信息
	 * @param vo
	 * @param req
	 * @param companyId
	 * @return
	 */
	public Map<String, Object> findCommodityProductUnitAllByStandardUnitId(Long addressId,Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Integer source,String channelProductId);

	public List<CommodityProductUnitDTO> findCommodityProductUnitLimit(CommodityProductUnitDTO dto);
	public Map<String, Object> findCommodityProductUnitStockByStandardUnitIdAddress(Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Integer source,Long addressId) ;
	public Map<String,Map<String, Object>> findCommodityProductUnitStockByStandardUnitIdsAddress(Long userId,Long companyId, List<Long> standardUnitId, Integer f, Long storeId,Integer source,Long addressId) ;

	//Long checkJdProductDetail(Long suId);
}
	