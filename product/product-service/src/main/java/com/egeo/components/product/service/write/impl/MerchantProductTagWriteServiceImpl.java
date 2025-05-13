package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProductTagWriteService;
import com.egeo.components.product.manage.write.MerchantProductTagWriteManage;
import com.egeo.components.product.converter.MerchantProductTagConverter;
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.components.product.po.MerchantProductTagPO;

@Service("merchantProductTagWriteService")
public class MerchantProductTagWriteServiceImpl  implements MerchantProductTagWriteService {
    @Autowired
    private MerchantProductTagWriteManage merchantProductTagWriteManage;

    @Override
    public Long insertMerchantProductTagWithTx(MerchantProductTagDTO dto) {
        MerchantProductTagPO po = MerchantProductTagConverter.toPO(dto);
        Long rt = merchantProductTagWriteManage.insertMerchantProductTagWithTx(po);
        return rt;
    }

    @Override
    public int updateMerchantProductTagWithTx(MerchantProductTagDTO dto) {
        MerchantProductTagPO po = MerchantProductTagConverter.toPO(dto);
        int rt = merchantProductTagWriteManage.updateMerchantProductTagWithTx(po);
        return rt;
    }

    @Override
    public int deleteMerchantProductTagWithTx(MerchantProductTagDTO dto) {
        MerchantProductTagPO po = MerchantProductTagConverter.toPO(dto);
        int rt = merchantProductTagWriteManage.deleteMerchantProductTagWithTx(po);
        return rt;
    }

    @Override
    public int delByMerchantProductId(Long merchantProductId) {
        return merchantProductTagWriteManage.delByMerchantProductId(merchantProductId);
    }
}
	