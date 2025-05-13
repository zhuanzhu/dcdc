package com.egeo.components.stock.service;

import java.util.List;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;

public interface StorePuWarehouseStockService {
	/**
	 * 更新门店pu库存信息
	 * @param type 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
	 * @param sodto
	 * @param soItems
	 * @return
	 */
	public Boolean updateStorePuWarehouseStock(int type,SoDTO sodto, List<SoItemDTO> soItems);
}
