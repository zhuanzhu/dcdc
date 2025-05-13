package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.CommodityProductUnitWarehouseStockManage;
import com.egeo.components.stock.facade.CommodityProductUnitWarehouseStockFacade;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("commodityProductUnitWarehouseStock")
public class CommodityProductUnitWarehouseStockManageImpl implements CommodityProductUnitWarehouseStockManage{
	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Resource(name="commodityProductUnitWarehouseStockFacade")
	private CommodityProductUnitWarehouseStockFacade commodityProductUnitWarehouseStockFacade;

	@Override
	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockById(CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockFacade.findCommodityProductUnitWarehouseStockById(dto);
	}

	@Override
	public PageResult<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockOfPage(CommodityProductUnitWarehouseStockDTO dto, Pagination page) {
		return commodityProductUnitWarehouseStockFacade.findCommodityProductUnitWarehouseStockOfPage(dto, page);
	}

	@Override
	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockFacade.findCommodityProductUnitWarehouseStockAll(dto);
	}

	@Override
	public Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockFacade.insertCommodityProductUnitWarehouseStockWithTx(dto);
	}

	@Override
	public int updateCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockFacade.updateCommodityProductUnitWarehouseStockWithTx(dto);
	}

	@Override
	public int deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockFacade.deleteCommodityProductUnitWarehouseStockWithTx(dto);
	}
	/**
	 * 进货
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int merchandiseStockWithTx(CommodityProductUnitWarehouseStockDTO dto,Long userId,String userName,String ip,String mac) {
		logger.info("business进货：puId:" + dto.getCommodityProductUnitId() + ",数量:"+ dto.getRealStockNum());
		return commodityProductUnitWarehouseStockFacade.merchandiseStockWithTx(dto,userId,userName,ip,mac);
	}
	/**
	 * 出货
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int deliverStockWithTx(CommodityProductUnitWarehouseStockDTO dto,Long userId,String userName,String ip,String mac) {
		logger.info("business出货：puId:" + dto.getCommodityProductUnitId() + ",数量:"+ dto.getRealStockNum());
		return commodityProductUnitWarehouseStockFacade.deliverStockWithTx(dto,userId,userName,ip,mac);
	}
	/**
	 * 根据puid查询pu库存信息
	 * @param id
	 * @return
	 */
	@Override
	public CommodityProductUnitWarehouseStockDTO findByProductUnitId(Long productUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockFacade.findByProductUnitId(productUnitId);
	}


}
	