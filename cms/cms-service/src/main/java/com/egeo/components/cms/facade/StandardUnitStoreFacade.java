package com.egeo.components.cms.facade;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.StandardUnitStoreClient;

/**
 * Created by 0.0 on 2018/10/13.
 */
@Component
public class StandardUnitStoreFacade {
    @Autowired
    private StandardUnitStoreClient standardUnitStoreReadService;


    public int finStandardUnitStoreBySu(Long suId, Long storeId) {
        return standardUnitStoreReadService.findStandardUnitStoreCount(suId,storeId);
    }
}
