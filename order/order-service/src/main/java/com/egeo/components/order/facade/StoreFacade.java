package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.client.StoreProductUnitClient;
import com.egeo.components.product.client.StoreTreeNodeClient;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

/**
 * Created by 0.0 on 2018/9/14.
 */
@Component
public class StoreFacade {

    @Autowired
    private StoreClient storeReadService;
    @Autowired
    private StoreTreeNodeClient storeTreeNodeReadService;
    @Autowired
    private StoreProductUnitClient storeProductUnitReadService;

    public StoreDTO findStoreById(Long storeId){
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(storeId);
        return storeReadService.findStoreById(storeDTO);
    }

    public List<StoreTreeNodeDTO> findStoreTreeNodeAll(StoreTreeNodeDTO dto) {
        return storeTreeNodeReadService.findStoreTreeNodeAll(dto);
    }

    public StoreProductUnitDTO findStorePuId(Long storeId, Long puId) {
        StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
        storeProductUnitDTO.setStoreId(storeId);
        storeProductUnitDTO.setCommodityProductUnitId(puId);
        List<StoreProductUnitDTO> storeProductUnitAll = storeProductUnitReadService.findStoreProductUnitAll(storeProductUnitDTO);
        if(EmptyUtil.isEmpty(storeProductUnitAll)){
            throw new BusinessException("没有对应的门店商品");
        }
        if(storeProductUnitAll.size()>1){
            throw new BusinessException("门店商品出现重复");
        }
        return storeProductUnitAll.get(0);
    }
}
