package com.egeo.components.third.business;

import com.egeo.components.third.dto.EnterpriseServiceRouteRequestDTO;
import com.egeo.components.third.dto.EnterpriseServiceRouteResponseDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseChannelServiceRouteManage {

    EnterpriseServiceRouteResponseDTO routeService(EnterpriseServiceRouteRequestDTO dto);
}
