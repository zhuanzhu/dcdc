package com.egeo.components.stock.business;

import java.util.List;
	
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitWarehouseStockManage {

	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockById(CommodityProductUnitWarehouseStockDTO dto);	

	public PageResult<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockOfPage(CommodityProductUnitWarehouseStockDTO dto,Pagination page);

	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockDTO dto);

	Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto);

	int updateCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto);

	int deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto);
	/**
	 * 进货
	 * @param vo
	 * @param req
	 * @return
	 */
	public int merchandiseStockWithTx(CommodityProductUnitWarehouseStockDTO dto,Long userId,String userName,String ip,String mac);
	/**
	 * 出货
	 * @param vo
	 * @param req
	 * @return
	 */
	public int deliverStockWithTx(CommodityProductUnitWarehouseStockDTO dto,Long userId,String userName,String ip,String mac);
	/**
	 * 根据puid查询pu库存信息
	 * @param id
	 * @return
	 */
	public CommodityProductUnitWarehouseStockDTO findByProductUnitId(Long productUnitId);
}
	