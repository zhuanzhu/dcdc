package com.egeo.components.third.business;

import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.web.JsonResult;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface RemoteExecuteManage {

    public JsonResult remoteExecute(RemoteExecuteDTO dto);
}
