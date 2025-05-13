package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelBaffleWriteService {

    public Long insertEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto);

    public int updateEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto);

    public int deleteEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto);
}
