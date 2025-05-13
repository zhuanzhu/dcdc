package com.egeo.components.third.strategy.impl;

import com.egeo.components.third.common.RouteRuteNameEnum;
import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.strategy.EnterpriseServiceRouteStrategy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("enterpriseServiceListSortImpl")
public class EnterpriseServiceListSortImpl implements EnterpriseServiceRouteStrategy {
    @Override
    public String getRouteRuteName() {
        return RouteRuteNameEnum.LIST_SORT.getRuteName();
    }

    @Override
    public List<EnterpriseChannelServiceDTO> filterEnterpriseChannelService(List<EnterpriseChannelServiceDTO> list, EnterpriseChannelServiceChoiceDTO ruteInfo) {
        List<EnterpriseChannelServiceDTO> sortedList = list.stream()
                .sorted(Comparator.comparing(EnterpriseChannelServiceDTO::getBizSort))
                .collect(Collectors.toList());
        return  sortedList;
    }
}
