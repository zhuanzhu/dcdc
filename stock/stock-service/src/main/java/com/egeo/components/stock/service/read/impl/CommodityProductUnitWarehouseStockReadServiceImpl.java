package com.egeo.components.stock.service.read.impl;

import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.stock.dto.ResidueStockNumConditionDTO;
import com.egeo.components.stock.po.ResidueStockNumCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.CommodityProductUnitWarehouseStockReadService;
import com.egeo.components.stock.manage.read.CommodityProductUnitWarehouseStockReadManage;
import com.egeo.components.stock.converter.CommodityProductUnitWarehouseStockConverter;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.EmptyUtil;

@Service("commodityProductUnitWarehouseStockReadService")
public class CommodityProductUnitWarehouseStockReadServiceImpl  implements CommodityProductUnitWarehouseStockReadService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6302893547087812716L;

	@Autowired
	private CommodityProductUnitWarehouseStockReadManage commodityProductUnitWarehouseStockReadManage;
	
	@Resource
	private  JedisUtil cache;

	@Override
	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockById(CommodityProductUnitWarehouseStockDTO dto) {
		CommodityProductUnitWarehouseStockPO po = CommodityProductUnitWarehouseStockConverter.toPO(dto);
		CommodityProductUnitWarehouseStockPO list = commodityProductUnitWarehouseStockReadManage.findCommodityProductUnitWarehouseStockById(po);		
		return CommodityProductUnitWarehouseStockConverter.toDTO(list);
	}

	@Override
	public PageResult<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockOfPage(CommodityProductUnitWarehouseStockDTO dto, Pagination page) {
		CommodityProductUnitWarehouseStockPO po = CommodityProductUnitWarehouseStockConverter.toPO(dto);
        PageResult<CommodityProductUnitWarehouseStockPO> pageResult = commodityProductUnitWarehouseStockReadManage.findCommodityProductUnitWarehouseStockOfPage(po, page);
        
        List<CommodityProductUnitWarehouseStockDTO> list = CommodityProductUnitWarehouseStockConverter.toDTO(pageResult.getList());
        PageResult<CommodityProductUnitWarehouseStockDTO> result = new PageResult<CommodityProductUnitWarehouseStockDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockDTO dto) {
		CommodityProductUnitWarehouseStockPO po = CommodityProductUnitWarehouseStockConverter.toPO(dto);
		List<CommodityProductUnitWarehouseStockPO> list = commodityProductUnitWarehouseStockReadManage.findCommodityProductUnitWarehouseStockAll(po);		
		return CommodityProductUnitWarehouseStockConverter.toDTO(list);
	}
	/**
	 * 根据puid查询pu库存信息(真实库存-冻结库存)
	 * @param id
	 * @return
	 */
	@Override
	public Long realStockNumByCommodityProductUnitId(Long commodityProductUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadManage.realStockNumByCommodityProductUnitId(commodityProductUnitId);
	}
	/**
	 * 根据puid查询pu库存信息
	 * @param puId
	 * @return
	 */
	@Override
	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockByPuId(Long puId) {
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = commodityProductUnitWarehouseStockReadManage.findCommodityProductUnitWarehouseStockByPuId(puId);
		return CommodityProductUnitWarehouseStockConverter.toDTO(commodityProductUnitWarehouseStockPO);
	}

	@Override
	public void setCommodityProductUnitWarehouseStockCache(
			CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO) {
		String key = "productUnit:inventory:" + commodityProductUnitWarehouseStockDTO.getCommodityProductUnitId();
		//Object json = JSONArray.toJSON(commodityProductUnitWarehouseStockDTO);
		cache.set(key, commodityProductUnitWarehouseStockDTO);
		System.out.println("===========日志===========: 已更新商品库存的缓存，商品id=" + commodityProductUnitWarehouseStockDTO.getCommodityProductUnitId() + ", 商品库存数量=" + (commodityProductUnitWarehouseStockDTO.getRealStockNum() - commodityProductUnitWarehouseStockDTO.getRealFrozenStockNum()) + ", key=" + key); 
		
	}

	@Override
	public CommodityProductUnitWarehouseStockDTO getCommodityProductUnitWarehouseStockCache(Long puId) {
		String key = "productUnit:inventory:" + puId;
		CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO = (CommodityProductUnitWarehouseStockDTO)cache.get(key);
		
		if(EmptyUtil.isNotEmpty(commodityProductUnitWarehouseStockDTO)) {
			return commodityProductUnitWarehouseStockDTO;
		}
		
		return null;
	}

	@Override
	public List<CommodityProductUnitWarehouseStockDTO> findByPUId(List<Long> commodityProductUnitIds) {
		List<CommodityProductUnitWarehouseStockPO> commodityProductUnitWarehouseStockList = null;
		if(EmptyUtil.isNotEmpty(commodityProductUnitIds)){
			//根据skuid查询pu库存
			commodityProductUnitWarehouseStockList = commodityProductUnitWarehouseStockReadManage.findByPUId(commodityProductUnitIds);
		}
		return CommodityProductUnitWarehouseStockConverter.toDTO(commodityProductUnitWarehouseStockList);
	}
	/**
	 * 根据suId查询剩余库存数量
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public Long residueStockNumByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadManage.residueStockNumByStandardUnitId(standardUnitId);
	}

	@Override
	public List<ResidueStockNumConditionDTO> residueStockNumByStandardUnitIds(List<Long> standardUnitIds) {
		List<ResidueStockNumCondition> list=commodityProductUnitWarehouseStockReadManage.residueStockNumByStandardUnitIds(standardUnitIds);
		return CommodityProductUnitWarehouseStockConverter.toDTOList(list);
	}

	/**
	 * 根据puId集合查询剩余库存数量
	 * @param skuId
	 * @return
	 */
	@Override
	public Long residueStockNumByPuId(List<Long> commodityProductUnitIds) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadManage.residueStockNumByPuId(commodityProductUnitIds);
	}
	/**
	 * 根据Skuid集合查询存在的sku库存信息
	 */
	@Override
	public List<Long> findIsSkuIdsBySkuIds(List<Long> skuIds) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadManage.findIsSkuIdsBySkuIds(skuIds);
	}

	@Override
	public Integer findPuSellOutSumByPuIds(List<Long> puIds) {
		return commodityProductUnitWarehouseStockReadManage.findPuSellOutSumByPuIds(puIds);
	}
}
	