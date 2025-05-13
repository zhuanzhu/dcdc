package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceWriteService {


    public Long insertEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto);

    public int updateEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto);

    public int deleteEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto);
}
