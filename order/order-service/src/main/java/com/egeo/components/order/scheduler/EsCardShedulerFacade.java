package com.egeo.components.order.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.common.DateUtils;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;


@Component
public class EsCardShedulerFacade {
	
	@Autowired
	private ECardClient eCardReadService;
	
	@Autowired
	private ECardClient eCardWriteService;
	
	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;

	public int updateECardWithTx(ECardDTO dto){
		
		return eCardWriteService.updateECardWithTx(dto);
	}
	/**
	 * 失效以过期的unit
	 */
	public void cardIsValid() {
		// 查询所有有效的unit
		ECardDTO eCardDTO = new ECardDTO();
		eCardDTO.setIsValid(1);
		List<ECardDTO> list = eCardReadService.findECardAll(eCardDTO);
		Map<Long, Long> map = new HashMap<>();
		for (ECardDTO eCardDTO2 : list) {
			// 获取当前时间
			long millis = DateUtils.curTimeMillis();
			// 判断当前时间是否在大于卡密使用结束时间
			if(millis > eCardDTO2.getEndTime().getTime()){
				// 不在则失效unit
				ECardDTO dto = new ECardDTO();
				dto.setId(eCardDTO2.getId());
				dto.setIsValid(0);
				eCardWriteService.updateECardWithTx(dto);
				// 判断是否存在该sku库存信息、不存在创建后put、存在则先获取在加一
				if(map.containsKey(eCardDTO2.getSkuId())){
					Long sum = map.get(eCardDTO2.getSkuId());
					map.put(eCardDTO2.getSkuId(), sum.longValue()+1L);
				}else{
					map.put(eCardDTO2.getSkuId(), 1L);
				}
			}
		}
		for (Map.Entry<Long, Long> entry : map.entrySet()) { 
			// 根据skuId扣除库存
			merchantProductVirtualStockWriteService.deductStockBySkuIdWithTx(entry.getKey(),entry.getValue());
		}
		
		
	}

}
	