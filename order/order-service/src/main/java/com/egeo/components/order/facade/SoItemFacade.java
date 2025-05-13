package com.egeo.components.order.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.order.service.write.SoItemWriteService;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.SoItemSkuAttValueDTO;
import com.egeo.components.promotion.client.ActivityClient;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.dto.ECardDTO;


@Component
public class SoItemFacade {
	
	@Resource
	private SoItemReadService soItemReadService;
	
	@Resource
	private SoItemWriteService soItemWriteService;
	
	@Autowired
	private SkuClient skuReadService;
	
	@Autowired
	private ActivityClient activityReadService;
	
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	@Autowired
	private ECardClient eCardReadService;
	
	@Resource
	private SoReadService soReadService;

	/**
	 * 查询订单项
	 * @param orderId
	 * @return
	 */
	public List<SoItemDTO> querySoItemListByOrderCode(String orderCode) {
		SoDTO soDTO = soReadService.querySoByOrderCode(orderCode);
		return soItemReadService.querySoItemBySoId(soDTO.getId());
	}

	/**
	 * 已知几个skuid,查询他们是由哪些规格组成的
	 * @param skuIdList
	 * @param platformId 
	 * @return
	 */
	public List<SoItemSkuAttValueDTO> querySkuValueListBySkuIds(List<Long> skuIdList, Long platformId) {
		return null;
		//return skuReadService.querySkuValueListBySkuIds(skuIdList,platformId);
	}

	public List<SoItemDTO> querySoItemListByPackId(Long packId, Long platformId) {
		
		return soItemReadService.querySoItemListByPackId(packId, platformId);
	}

	public Long updateSoItem(SoItemDTO dto) {
		
		return soItemWriteService.updateSoItemWithTx(dto);
	}

	/**
	 * 查询商品是否过期
	 * @param id
	 * @return true 未过期 false已过期
	 */
	public boolean queryMerchantProductActivityExpired(Long mpId) {
		return activityReadService.activityByMerchantProdIdAndDate(mpId);
	}

	/**
	 * 根据条件查询所有
	 * @param soItemDTO
	 * @return
	 */
	public List<SoItemDTO> findAllSoItem(SoItemDTO soItemDTO) {
		return soItemReadService.findAll(soItemDTO);
	}
	/**
	 * 根据订单ID查询订单项
	 * @param id
	 * @return
	 */
	public List<SoItemDTO> querySoItemListBySoId(Long id) {
		return soItemReadService.querySoItemListBySoId(id);
	}

	public List<ECardDTO> findECard(ECardDTO eCardDTO) {
		
		return eCardReadService.findECardAll(eCardDTO);
	}

	/**根据puid查询商品编号
	 * @param commodityProductUnitDTO
	 * @return
	 */
	public CommodityProductUnitDTO findPU(CommodityProductUnitDTO commodityProductUnitDTO) {
		return commodityProductUnitReadService.findCommodityProductUnitById(commodityProductUnitDTO);
	}

	/**
	 * 根据子订单id查询订单项列表
	 * @param id
	 * @return
	 */
	public List<SoItemDTO> querySoItemsBySoChildId(Long id) {
		
		return soItemReadService.querySoItemsBySoChildId(id);
	}


    public Long findPUNum(Long id) {
		return soItemReadService.findPUNum(id);
    }
}
	