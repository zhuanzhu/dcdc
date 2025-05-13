package com.egeo.components.product.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CommodityProductUnitManage;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.facade.CommodityProductUnitFacade;
import com.egeo.components.product.facade.StandardUnitFacade;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.cache.JedisUtil;

@Service("commodityProductUnit")
public class CommodityProductUnitManageImpl implements CommodityProductUnitManage {

	@Resource(name = "commodityProductUnitFacade")
	private CommodityProductUnitFacade commodityProductUnitFacade;

	@Resource(name = "standardUnitFacade")
	private StandardUnitFacade standardUnitFacade;

	@Autowired
	private JedisUtil jedisUtil;

	@Override
	public CommodityProductUnitDTO findCommodityProductUnitById(CommodityProductUnitDTO dto) {
		return commodityProductUnitFacade.findCommodityProductUnitById(dto);
	}

	@Override
	public PageResult<CommodityProductUnitDTO> findCommodityProductUnitOfPage(CommodityProductUnitDTO dto,
			Pagination page) {
		return commodityProductUnitFacade.findCommodityProductUnitOfPage(dto, page);
	}

	@Override
	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(CommodityProductUnitDTO dto) {
		return commodityProductUnitFacade.findCommodityProductUnitAll(dto);
	}

	@Override
	public Long insertCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {
		return commodityProductUnitFacade.insertCommodityProductUnitWithTx(dto);
	}

	@Override
	public int updateCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {
		return commodityProductUnitFacade.updateCommodityProductUnitWithTx(dto);
	}

	@Override
	public int deleteCommodityProductUnitWithTx(CommodityProductUnitDTO dto) {
		return commodityProductUnitFacade.deleteCommodityProductUnitWithTx(dto);
	}

	/**
	 * 分页查询pu库存信息
	 *
	 * @param vo
	 * @param req
     * @param page  @return
	 */
	@Override
	public PageResult<Map<String, Object>> findCommodityProductUnitStockOfPage(CommodityProductUnitDTO dto,
                                                                               Pagination page) {
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		PageResult<CommodityProductUnitDTO> result = commodityProductUnitFacade.findCommodityProductUnitOfPage(dto,
				page);
		List<CommodityProductUnitDTO> commodityProductUnitList = result.getList();
		List<Map<String, Object>> PUList = new ArrayList<>();
		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			Map<String, Object> map2 = new HashMap<>();
			map2.put("productUnitId", commodityProductUnitDTO2.getId());
			map2.put("productUnitSerialNumber", commodityProductUnitDTO2.getProductUnitSerialNumber());
			map2.put("name", commodityProductUnitDTO2.getName());
			map2.put("status", commodityProductUnitDTO2.getStatus());
			if(commodityProductUnitDTO2.getEnterpriseId()!=null) {
				map2.put("enterpriseId", commodityProductUnitDTO2.getEnterpriseId());
			}
			// 根据puid查询pu库存信息
			CommodityProductUnitWarehouseStockDTO productUnitWarehouseStockDTO = new CommodityProductUnitWarehouseStockDTO();
			productUnitWarehouseStockDTO.setCommodityProductUnitId(commodityProductUnitDTO2.getId());
			List<CommodityProductUnitWarehouseStockDTO> CommodityProductUnitWarehouseStockList = commodityProductUnitFacade
					.findCommodityProductUnitWarehouseStockAll(productUnitWarehouseStockDTO);
			if (CommodityProductUnitWarehouseStockList.size() == 1) {
				map2.put("commodityProductUnitWarehouseStockId", CommodityProductUnitWarehouseStockList.get(0).getId());
				// 在线库存
				map2.put("realStockNum", CommodityProductUnitWarehouseStockList.get(0).getRealStockNum());
				// 真实冻结库存
				map2.put("realFrozenStockNum", CommodityProductUnitWarehouseStockList.get(0).getRealFrozenStockNum());
			} else if (CommodityProductUnitWarehouseStockList.size() > 1) {
				throw new BusinessException(commodityProductUnitDTO2.getId() + "pu库存信息不唯一");
			}

			// 根据skuid查询水库库存信息
			MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO = new MerchantProductWarehouseStockDTO();
			merchantProductWarehouseStockDTO.setSkuId(commodityProductUnitDTO2.getSkuId());
			List<MerchantProductWarehouseStockDTO> merchantProductWarehouseStockList = commodityProductUnitFacade
					.merchantProductWarehouseStockFindAll(merchantProductWarehouseStockDTO);
			if (merchantProductWarehouseStockList.size() == 1) {
				map2.put("skuRealStockNum", merchantProductWarehouseStockList.get(0).getRealFrozenStockNum());
			} else if (CommodityProductUnitWarehouseStockList.size() > 1) {
				throw new BusinessException(commodityProductUnitDTO2.getSkuId() + "sku库存信息不唯一");
			}
			map2.put("storeName", commodityProductUnitDTO2.getStoreName());
			// su信息
			map2.put("merchantId", commodityProductUnitDTO2.getMerchantId());
			map2.put("merchantName", commodityProductUnitDTO2.getStandardUnitName());
			map2.put("isVisible", commodityProductUnitDTO2.getIsVisible());
			PUList.add(map2);
		}

		pageResult.setList(PUList);
		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		return pageResult;
	}

	/**
	 * 根据suid查询su所有pu信息
	 *
	 * @param vo
	 * @param req
	 * @param companyId
	 * @return
	 */
	@Override
	public Map<String, Object> findCommodityProductUnitAllByStandardUnitId(Long addressId,Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Integer source,String channelProductId) {
		if(source.intValue()==3) {
			return commodityProductUnitFacade.findJdCommodityProductUnitAllByStandardUnitId(userId,companyId,standardUnitId,f, storeId,addressId);
		}else if(source.intValue() ==4){
			return commodityProductUnitFacade.findCakeCommodityProductUnitAllByStandardUnitId(userId,companyId,standardUnitId,f, storeId,addressId,channelProductId);
		}else if(source.intValue() ==5){
			return commodityProductUnitFacade.findWorldBuyCommodityProductUnitAllByStandardUnitId(userId,companyId,standardUnitId,f, storeId,addressId,channelProductId);
		}
		return commodityProductUnitFacade.findCommodityProductUnitAllByStandardUnitId(userId,companyId,standardUnitId,f, storeId);
	}

	@Override
	public Map<String, Object> findCommodityProductUnitStockByStandardUnitIdAddress(Long userId,Long companyId, Long standardUnitId, Integer f, Long storeId,Integer source,Long addressId) {
		if(source.intValue()==3) {
			return commodityProductUnitFacade.findJdStockStatus(userId,companyId,standardUnitId,f, storeId,addressId);
		}
		return new HashMap<String, Object>();
	}
	@Override
	public Map<String,Map<String, Object>> findCommodityProductUnitStockByStandardUnitIdsAddress(Long userId,Long companyId, List<Long> standardUnitIds, Integer f, Long storeId,Integer source,Long addressId) {
		Map<String, Map<String, Object>> rslt = new HashMap<String,Map<String, Object>>();
		if(source.intValue()==3 && standardUnitIds.size()>0) {
			for(Long standardUnitId:standardUnitIds) {
				if(standardUnitId!=null && standardUnitId>0) {
					rslt.put(standardUnitId.longValue()+"", findCommodityProductUnitStockByStandardUnitIdAddress(userId, companyId, standardUnitId, f, storeId, source, addressId));
				}
			}
		}
		return rslt;
	}
	@Override
	public List<CommodityProductUnitDTO> findCommodityProductUnitLimit(CommodityProductUnitDTO dto) {
		// TODO Auto-generated method stub
		return commodityProductUnitFacade.findCommodityProductUnitLimit(dto);
	}

	/*@Override
	public Long checkJdProductDetail(Long suId) {
		return commodityProductUnitFacade.checkJdProductDetail(jedisUtil, suId);
	}*/

}
