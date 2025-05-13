package com.egeo.components.order.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;

/**
 * Created by 0.0 on 2018/9/14.
 */
@Component
public class CommodityProductUnitFacade {
	@Autowired
    private CommodityProductUnitClient commodityProductUnitReadService;

    public CommodityProductUnitDTO findCommodityProductUnitById(CommodityProductUnitDTO puCond){
        return commodityProductUnitReadService.findCommodityProductUnitById(puCond);
    }


}
