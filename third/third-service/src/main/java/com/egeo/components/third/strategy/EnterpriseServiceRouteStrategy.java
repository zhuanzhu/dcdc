package com.egeo.components.third.strategy;

import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;

import java.util.List;

/**
 * @Description 企业可用渠道筛选策略
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseServiceRouteStrategy {

    public String getRouteRuteName();

    List<EnterpriseChannelServiceDTO> filterEnterpriseChannelService(List<EnterpriseChannelServiceDTO> list, EnterpriseChannelServiceChoiceDTO ruteInfo);
}
