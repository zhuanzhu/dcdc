package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.PayTypeClient;
import com.egeo.components.user.dto.PayTypeDTO;

/**
 * Created by 0.0 on 2018/8/30.
 */
@Component("cmsPayTypeFacade")
public class CmsPayTypeFacade {
	@Autowired
    private PayTypeClient payTypeReadService;

    public PayTypeDTO getPayTypeByCode(Integer payTypeCode){
        return payTypeReadService.findPayTypeByCode(payTypeCode);
    }


    public List<PayTypeDTO> getPayTypeByCodes(List<Integer> collect) {
        return payTypeReadService.findPayTypeByCodes(collect);
    }
}
