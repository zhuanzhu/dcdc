package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceChoiceWriteService {


    public Long insertEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto);

    public int updateEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto);

    public int deleteEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto);

}
